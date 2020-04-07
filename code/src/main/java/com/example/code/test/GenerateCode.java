package com.example.code.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

/*
    生成二维码
 */
@Slf4j
public class GenerateCode {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 写入二维码、以及将照片logo写入二维码中
     * @param matrix 要写入的二维码
     * @param format 二维码照片格式
     * @param imagePath 二维码照片保存路径
     * @param logoPath logo路径
     * @param logoConfig logo配置对象
     * @throws IOException
     */
    public static void main(String[] args) {
        try {
            String format = "jpg";
            BufferedImage qrCode_img = generateCode("518");
            String imagePath = "D:/java/qrCode/code.jpg"; //和生成的图片路径一直
            String logoPath = "https://www.xingfuh.com/HappyHui/upload/activity/d5c70d80-d74b-49a4-bfeb-e06971bc65a2.png";

            //GenerateCode.overlapImage(qrCode_img, format, imagePath, logoPath, new MatrixToLogoImageConfig());

                System.out.println("成功生成二维码");

        } catch (WriterException | IOException e) {
            System.err.println("生成二维码失败");
            e.printStackTrace();
        }

    }

    public static BufferedImage generateCode(String productId) throws WriterException, IOException {
        // 这里是URL，扫描之后就跳转到这个界面
        String text = "http://www.xingfuh.com/HappyHui/invisibleMountain/auidoCourseShare.html?share_user_id=49450&course_id=hc2020021419390503168377&user_id=34590&open_id=oIqbnvuGE0Nfw16CWMrmXZTI2Y-Q&type=weixin";

        //String path = "D:/java/qrCode"; // 图片生成的位置
        String path = "D:/java/qrCode/code.jpg"; // 图片生成的位置
        int width = 400;
        int height = 400;
        // 二维码图片格式
        String format = "jpg";
        // 设置编码，防止中文乱码
        Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object>();
        ht.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码参数(编码内容，编码类型，图片宽度，图片高度,格式)
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, ht);
        // 生成二维码(定义二维码输出服务器路径)
        File outputFile = new File(path);
        if (!outputFile.exists()) {
            // 创建文件夹
            outputFile.mkdirs();
        }

        int b_width = bitMatrix.getWidth();
        int b_height = bitMatrix.getHeight();
        // 建立图像缓冲器
        BufferedImage image = new BufferedImage(b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < b_width; x++) {
            for (int y = 0; y < b_height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        // 生成二维码



        ImageIO.write(image, format,outputFile);
        // 二维码的名称


        //对二维码的路径重新读取
        //BufferedImage img = ImageIO.read(new File(imagePath));


        return null;
    }
    // 给二维码添加logo

    /**
     * 将照片logo添加到二维码中间
     * @param image 生成的二维码照片对象
     * @param imagePath 照片保存路径
     * @param logoPath logo照片路径
     * @param formate 照片格式
     */
    public static void overlapImage(BufferedImage image, String formate, String imagePath, String logoPath, MatrixToLogoImageConfig logoConfig) {
        try {
            URL url = new URL(logoPath);
            HttpURLConnection conn =(HttpURLConnection)url.openConnection();

            //BufferedImage logo = ImageIO.read(new File(url.toURI())); //File URL InputStream 三者都可以
            BufferedImage logo =  ImageIO.read(url);
            Graphics2D g = image.createGraphics();
            //考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
            int width = image.getWidth() / logoConfig.getLogoPart();
            int height = image.getHeight() / logoConfig.getLogoPart();
            //logo起始位置，此目的是为logo居中显示
            int x = (image.getWidth() - width) / 2;
            int y = (image.getHeight() - height) / 2;
            //绘制图
            g.drawImage(logo, x, y, width, height, null);

            //给logo画边框
            //构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
            g.setStroke(new BasicStroke(logoConfig.getBorder()));
            g.setColor(logoConfig.getBorderColor());
            g.drawRect(x, y, width, height);

            g.dispose();
            //写入logo照片到二维码
            ImageIO.write(image, formate, new File(imagePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
