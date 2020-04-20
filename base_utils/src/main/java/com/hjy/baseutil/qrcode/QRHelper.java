package com.hjy.baseutil.qrcode;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.hjy.baseutil.qrcode.CodeHints;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class QRHelper {

    public static String getReult(Bitmap mBitmap) {
        String string = null;
        if (mBitmap != null) {
            string = scanBitmap(mBitmap);
        }
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        return null;
    }

    private static String scanBitmap(Bitmap mBitmap) {
        Result result = scan(mBitmap);
        if (result != null) {
            return recode(result.toString());
        } else {
            return null;
        }
    }

    private static String recode(String str) {
        String formart = "";
        try {
            boolean ISO = Charset.forName("ISO-8859-1").newEncoder()
                    .canEncode(str);
            if (ISO) {
                formart = new String(str.getBytes("ISO-8859-1"), "GB2312");
            } else {
                formart = str;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return formart;
    }

    private static Result scan(Bitmap mBitmap) {
//        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 设置二维码内容的编码
//        Bitmap scanBitmap = Bitmap.createBitmap(mBitmap);
//
//        int px[] = new int[scanBitmap.getWidth() * scanBitmap.getHeight()];
//        scanBitmap.getPixels(px, 0, scanBitmap.getWidth(), 0, 0,
//                scanBitmap.getWidth(), scanBitmap.getHeight());
//        RGBLuminanceSource source = new RGBLuminanceSource(
//                scanBitmap.getWidth(), scanBitmap.getHeight(), px);
//        BinaryBitmap tempBitmap = new BinaryBitmap(new HybridBinarizer(source));
//        QRCodeReader reader = new QRCodeReader();
//
//        try {
//            return reader.decode(tempBitmap, hints);
//        } catch (NotFoundException e) {
//        } catch (ChecksumException e) {
//        } catch (FormatException e) {
//        }
//        return null;
        com.google.zxing.Result result = null;
        if (mBitmap != null) {
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();
            int[] pixels = new int[width * height];
            mBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            // 新建一个RGBLuminanceSource对象
            RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
            // 将图片转换成二进制图片
            BinaryBitmap binaryBitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            QRCodeReader reader = new QRCodeReader();// 初始化解析对象
            try {
                result = reader.decode(binaryBitmap, CodeHints.getDefaultDecodeHints());// 开始解析
            } catch (NotFoundException e) {

            } catch (ChecksumException e) {

            } catch (FormatException e) {

            }
        }
        return result;
    }
}
