package com.hjy.gamecommunity.fragment.message;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baseui.adapter.BaseAdapter;
import com.hjy.baseui.ui.BaseFragment;
import com.hjy.baseui.ui.divider.HorizontalDividerItemDecoration;
import com.hjy.baseutil.ToastUtil;
import com.hjy.baseutil.ViewSeting;
import com.hjy.gamecommunity.R;
import com.hjy.gamecommunity.adapter.message.FamilyMsgAdapter;
import com.hjy.gamecommunity.database.Conversation;
import com.hjy.gamecommunity.enumclass.FamilyMsgOperationEnum;
import com.hjy.gamecommunity.popupwindow.ListPopup;
import com.hjy.gamecommunity.utils.GenerateTestUserSig;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMConversationListener;
import com.tencent.imsdk.v2.V2TIMConversationResult;
import com.tencent.imsdk.v2.V2TIMGroupInfo;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMSendCallback;
import com.xuexiang.xui.adapter.simple.XUISimpleAdapter;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.popupwindow.popup.XUIPopup;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/28 17:48
 * 描述:  家族消息
 */
public class FragmentFamilyMsg extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private FamilyMsgAdapter familyMsgAdapter;//

    @Override
    public int getLayoutId() {
        return R.layout.fragment_family_msg;
    }

    @Override
    public void initView(View mRootView) {
        mRecyclerView = findViewById(R.id.recyclerView);
        mSmartRefreshLayout = findViewById(R.id.smartRefreshLayout);

    }

    @Override
    public void onFragmentVisibleChange(boolean isVisible) {

    }


    @Override
    public void initData() {
        //RecyclerView分割线
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//            mRecyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速才会有虚线效果
//        }
        Paint paint = new Paint();
        paint.setStrokeWidth(ConvertUtils.dp2px(1));//分割线宽度
        paint.setColor(ContextCompat.getColor(getActivity(), R.color.gray_light3));//分割线颜色
        //DashPathEffect作用是将Path的线段虚线化--
        // 第一个参数是数组，且它的长度必须>=2, 数组的数字就是控制实虚... 长度。
        // phase:为绘制时的偏移量
        // paint.setPathEffect(new DashPathEffect(new float[]{20f, 10f}, 0));//虚线
        HorizontalDividerItemDecoration build = new HorizontalDividerItemDecoration.Builder(getContext())//横向分割线
                .paint(paint)
                .margin(ConvertUtils.dp2px(12), 0)//分割线margin
                .showLastDivider()//显示最后一条
                .startSkipCount(0)//跳过开头的*个分割线不展示
                //.endSkipCount(1)//跳过结尾的2个分割线不展示
                .build();
        mRecyclerView.addItemDecoration(build);
        //Adapter
        familyMsgAdapter = new FamilyMsgAdapter();
        mRecyclerView.setAdapter(familyMsgAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置 Header 样式
        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
        mSmartRefreshLayout.setRefreshHeader(classicsHeader);
        //设置 Footer  样式
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));

        mSmartRefreshLayout.setEnableAutoLoadMore(false);//是否启用列表惯性滑动到底部时自动加载更多
        //设置刷新加载时禁止所有列表操作
        mSmartRefreshLayout.setDisableContentWhenRefresh(true);
        mSmartRefreshLayout.setDisableContentWhenLoading(true);


        mSmartRefreshLayout.autoRefresh();//自动刷新

    }

    /**
     * nextSeq	分页拉取的游标，第一次默认取传 0，后续分页拉传上一次分页拉取成功回调里的 nextSeq
     * count	分页拉取的个数，一次分页拉取不宜太多，会影响拉取的速度，建议每次拉取 100 个会话
     */
    private long nextSeq = 0;
    private int count = 30;
    private boolean finished;//如果会话全部拉取完毕，成功回调里面 V2TIMConversationResult 中的  isFinished 获取字段值为 true。
    private boolean refreshOrLoadMore;//true:下拉刷新  false:上拉加载更多

    @Override
    public void listener() {

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshOrLoadMore = true;

                getConversationList(nextSeq, count);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshOrLoadMore = false;

                if (finished) {
                    closeFinish();
                    ToastUtil.tost("没有更多啦!");
                } else {
                    //获取会话列表
                    getConversationList(nextSeq, count);

                }

            }

        });

        familyMsgAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<V2TIMConversation>() {
            /**
             * •V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_MESSAGE：在线正常接收消息，离线时会有厂商的离线推送通知。
             * •V2TIMGroupInfo.V2TIM_GROUP_NOT_RECEIVE_MESSAGE：不会接收到群消息。
             * •V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_NOT_NOTIFY_MESSAGE：在线正常接收消息，离线不会有推送
             *
             *  // V2TIMManager.getGroupManager().setReceiveMessageOpt();
             */
            @Override
            public void onItemClick(View view, V2TIMConversation item, int position) {
                int recvOpt = item.getRecvOpt();//获取消息接收选项（群会话有效）


                boolean isRemind = false;//当前消息是否提醒
                if (recvOpt == V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_MESSAGE) {
                    isRemind = true;
                }

                showMsgOperationPopup(view, item, false, false, isRemind);

            }
        });
        //设置会话监听器
        V2TIMManager.getConversationManager().setConversationListener(new V2TIMConversationListener() {
            /**
             * 有新的会话（比如收到一个新同事发来的单聊消息、或者被拉入了一个新的群组中），
             * 可以根据会话的 lastMessage -> timestamp 重新对会话列表做排序
             * @param conversationList
             */
            @Override
            public void onNewConversation(List<V2TIMConversation> conversationList) {
                super.onNewConversation(conversationList);
                Log.d("FragmentFamilyMsg", "有新的会话（比如收到一个新同事发来的单聊消息、或者被拉入了一个新的群组中）");

                if (conversationList != null && conversationList.size() > 0) {
                    familyMsgAdapter.addItemsToLast(conversationList);
                }
            }

            /**
             * 某些会话的关键信息发生变化（未读计数发生变化、最后一条消息被更新等等），
             * 可以根据会话的 lastMessage -> timestamp 重新对会话列表做排序
             * @param conversationList
             */
            @Override
            public void onConversationChanged(List<V2TIMConversation> conversationList) {
                super.onConversationChanged(conversationList);
                Log.d("FragmentFamilyMsg", "某些会话的关键信息发生变化（未读计数发生变化、最后一条消息被更新等等）");

                if (conversationList != null && conversationList.size() > 0) {
                    if (refreshOrLoadMore)
                        familyMsgAdapter.replaceAll(conversationList);
                    else
                        familyMsgAdapter.addItemsToLast(conversationList);
                }
            }
        });
    }


    /**
     * 获取家族消息列表
     *
     * @param nextSeq 分页拉取的游标，第一次默认取传 0，后续分页拉传上一次分页拉取成功回调里的 nextSeq
     * @param count 分页拉取的个数，一次分页拉取不宜太多，会影响拉取的速度，建议每次拉取 100 个会话
     */
    private boolean isIMLogin;//即时通讯用户是否登录

    private void getConversationList(long nextSeq, int count) {
        if (isIMLogin) {
            //获取会话列表
            V2TIMManager.getConversationManager().getConversationList(nextSeq, count, getV2TIMSendCallback);
        } else {
            UserData userData = UserDataContainer.getInstance().getUserData();
            if (userData != null) {
                String user_id = userData.getUser_id();
                String genTestUserSig = GenerateTestUserSig.genTestUserSig(user_id);
                V2TIMManager.getInstance().login(user_id, genTestUserSig, new V2TIMCallback() {
                    @Override
                    public void onError(int i, String s) {
                        ToastUtils.toast("IM登录失败！-" + s);
                    }

                    @Override
                    public void onSuccess() {
                        isIMLogin = true;
                        //获取会话列表
                        V2TIMManager.getConversationManager().getConversationList(nextSeq, count, getV2TIMSendCallback);
                    }
                });
            } else {
                ToastUtils.toast("您还未登录");
                closeFinish();
            }
        }

    }

    /**
     * 初始化列表弹出
     *
     * @param view 相对于这个view的某个位置弹出
     * @param isRead 消息是否已读
     * @param isTop 消息是否消置顶
     * @param isRemind 是否新消息提醒
     */
    private ListPopup mListPopup;

    private void showMsgOperationPopup(View view, V2TIMConversation v2TIMConversation, boolean isRead, boolean isTop, boolean isRemind) {
        if (mListPopup != null && mListPopup.isShowing()) {
            mListPopup.dismiss();
        }

        final List<String> stringList = new ArrayList<>();
        if (isRead) {
            //如果消息已读，则标记消息可设置未读
            stringList.add(FamilyMsgOperationEnum.UN_READ);
        } else {
            stringList.add(FamilyMsgOperationEnum.READ);
        }

        if (isTop) {
            //如果消息已置顶，则标记消息可设置取消置顶
            stringList.add(FamilyMsgOperationEnum.UN_TOP);
        } else {
            stringList.add(FamilyMsgOperationEnum.TOP);
        }
        stringList.add(FamilyMsgOperationEnum.REMOVE);

        if (isRemind) {
            //如果消息已提醒，则标记消息可设置取消提醒
            stringList.add(FamilyMsgOperationEnum.UN_REMIND);
        } else {
            stringList.add(FamilyMsgOperationEnum.REMIND);
        }

        XUISimpleAdapter adapter = XUISimpleAdapter.create(getContext(), stringList.toArray(new String[]{}));
        mListPopup = new ListPopup(getContext(), adapter);

        /**
         * •V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_MESSAGE：在线正常接收消息，离线时会有厂商的离线推送通知。
         * •V2TIMGroupInfo.V2TIM_GROUP_NOT_RECEIVE_MESSAGE：不会接收到群消息。
         * •V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_NOT_NOTIFY_MESSAGE：在线正常接收消息，离线不会有推送
         *
         *   V2TIMManager.getGroupManager().setReceiveMessageOpt();
         */
        mListPopup.create(DensityUtils.dp2px(110), DensityUtils.dp2px(165), new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (stringList.get(i)) {
                    case FamilyMsgOperationEnum.READ://标记已读
                        break;
                    case FamilyMsgOperationEnum.UN_READ://标记未读
                        break;
                    case FamilyMsgOperationEnum.TOP://消息置顶
                        break;
                    case FamilyMsgOperationEnum.UN_TOP://取消消息置顶
                        break;
                    case FamilyMsgOperationEnum.REMOVE://移除消息
                        break;
                    case FamilyMsgOperationEnum.REMIND://消息提醒
                        V2TIMManager.getGroupManager().setReceiveMessageOpt(v2TIMConversation.getGroupID(), V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_MESSAGE, new V2TIMCallback() {
                            @Override
                            public void onError(int i, String s) {
                                ToastUtils.toast("消息提醒设置失败-" + s);
                            }

                            @Override
                            public void onSuccess() {
                                ToastUtils.toast("消息提醒设置成功");
                                // mSmartRefreshLayout.autoRefresh();//自动刷新
                                // familyMsgAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case FamilyMsgOperationEnum.UN_REMIND://取消消息提醒

                        V2TIMManager.getGroupManager().setReceiveMessageOpt(v2TIMConversation.getGroupID(), V2TIMGroupInfo.V2TIM_GROUP_RECEIVE_NOT_NOTIFY_MESSAGE, new V2TIMCallback() {
                            @Override
                            public void onError(int i, String s) {
                                ToastUtils.toast("取消提醒失败-" + s);
                            }

                            @Override
                            public void onSuccess() {
                                ToastUtils.toast("取消提醒成功");
                                // mSmartRefreshLayout.autoRefresh();//自动刷新
                                //familyMsgAdapter.notifyDataSetChanged();
                            }

                        });
                        break;
                }
                mListPopup.dismiss();
            }
        });
        mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        mListPopup.setAnimStyle(XUIPopup.ANIM_GROW_FROM_CENTER);//弹出动画


        int rightMargin = DensityUtils.dp2px(62);
        int offsetY = DensityUtils.dp2px(25);
        mListPopup.setPositionOffsetX(rightMargin);//设置根据计算得到的位置后的偏移值
        mListPopup.setPositionOffsetYWhenBottom(-offsetY);//DIRECTION_BOTTOM 时的 offsetY
        mListPopup.setPositionOffsetYWhenTop(offsetY);


        int[] location = new int[2];
        //取在整个屏幕内的绝对坐标
        view.getLocationOnScreen(location);
        //view距离window 左边的距离（即x轴方向
        // int contentViewX = location[0];
        // view距离window 顶边的距离（即y轴方向）
        int contentViewY = location[1];

        //view中心坐标 y轴
        if (contentViewY >= ViewSeting.getScreenHeight() * 0.6) {
            mListPopup.setPreferredDirection(XUIPopup.DIRECTION_TOP);//弹出方向
        } else {
            mListPopup.setPreferredDirection(XUIPopup.DIRECTION_BOTTOM);//弹出方向
        }
        mListPopup.show(view);
    }

    private void closeFinish() {
        mSmartRefreshLayout.finishRefresh(200);
        mSmartRefreshLayout.finishLoadMore(200);
    }

    /**
     * 添加数据到本地数据库
     *
     * @param conversationList
     */
    private void commitDatabase(List<V2TIMConversation> conversationList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<Conversation> users = new ArrayList<>();
        for (V2TIMConversation v2TIMConversation : conversationList) {
            Conversation conversation = new Conversation();
        }
        realm.insert(users);
        realm.commitTransaction();
    }

    /**
     * 添加数据到本地数据库
     */
    private void commitDatabase(V2TIMConversation v2TIMConversation) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Conversation user = realm.createObject(Conversation.class);
        // user.setGroupID();

        realm.commitTransaction();
    }

    /**
     * 群消息会话列表
     */
    V2TIMSendCallback getV2TIMSendCallback = new V2TIMSendCallback<V2TIMConversationResult>() {
        @Override
        public void onProgress(int i) {

        }

        @Override
        public void onError(int i, String s) {
            closeFinish();
        }

        /**
         *  获取会话列表
         *
         * 一个会话对应一个聊天窗口，比如跟一个好友的 1v1 聊天，或者一个聊天群，都是一个会话。
         * 由于历史的会话数量可能很多，所以该接口希望您采用分页查询的方式进行调用，
         * 该接口拉取的是本地缓存的会话，如果服务器会话有更新，SDK 内部会自动同步，然后在 V2TIMConversationListener 回调告知客户。
         * 该接口获取的会话列表默认已经按照会话 lastMessage -> timestamp 做了排序，timestamp 越大，会话越靠前。
         * 如果会话全部拉取完毕，成功回调里面 V2TIMConversationResult 中的 isFinished 获取字段值为 true。
         * 参数
         * nextSeq	分页拉取的游标，第一次默认取传 0，后续分页拉传上一次分页拉取成功回调里的 nextSeq
         * count	分页拉取的个数，一次分页拉取不宜太多，会影响拉取的速度，建议每次拉取 100 个会话
         * @param v2TIMConversationResult
         */
        @Override
        public void onSuccess(V2TIMConversationResult v2TIMConversationResult) {
            Log.d("FragmentFamilyMsg", "获取到会话列表");
            closeFinish();

            List<V2TIMConversation> conversationList = v2TIMConversationResult.getConversationList();

            if (conversationList != null && conversationList.size() > 0) {
                if (refreshOrLoadMore)
                    familyMsgAdapter.replaceAll(conversationList);
                else
                    familyMsgAdapter.addItemsToLast(conversationList);
            } else {
                if (refreshOrLoadMore) {
                    //  ToastUtil.tost("无更新消息");
                } else {
                    ToastUtil.tost("没有更多啦!");
                }
            }

            finished = v2TIMConversationResult.isFinished();
            nextSeq = v2TIMConversationResult.getNextSeq();
        }
    };

    /**
     * 获取已加入的群组回调
     */
//    V2TIMSendCallback v2TIMSendCallback = new V2TIMSendCallback<List<V2TIMGroupInfo>>() {
//        @Override
//        public void onProgress(int i) {
//            Log.d("v2TIMSendCallback", "Progress:" + i);
//        }
//
//        @Override
//        public void onError(int i, String s) {
//            Log.d("v2TIMSendCallback", "i:" + i);
//            Log.d("v2TIMSendCallback", "Error:" + s);
//            mSmartRefreshLayout.finishRefresh(200);
//            mSmartRefreshLayout.finishLoadMore(200);
//        }
//
//        @Override
//        public void onSuccess(List<V2TIMGroupInfo> v2TIMGroupInfos) {
//            mSmartRefreshLayout.finishRefresh(200);
//            mSmartRefreshLayout.finishLoadMore(200);
//
//            if (v2TIMGroupInfos != null && v2TIMGroupInfos.size() > 0) {
//                if (pageVideo == 1)
//                    familyMsgAdapter.replaceAll(v2TIMGroupInfos);
//                else
//                    familyMsgAdapter.addItemsToLast(v2TIMGroupInfos);
//            } else {
//                if (pageVideo == 1) {
//                    ToastUtil.tost("您还没有家族");
//                } else {
//                    ToastUtil.tost("没有更多啦!");
//                }
//            }
//
//        }
//    };


}
