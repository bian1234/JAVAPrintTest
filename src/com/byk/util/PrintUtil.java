package com.byk.util;

import com.byk.entity.Persion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * @Author: ykbian
 * @Date: 2018/10/24 9:24
 * @Todo: 打印的工具类
 */
public class PrintUtil implements Printable {

//    身份证：长度85.6毫米,宽度54毫米,厚度0.9毫米
    //待打印数据的条数，此变量需定义在数据集合之前
    private static int COUNT = 0;
    //待打印的文字数据
    private static List<Persion> STUDENT_LIST = getStudent();
    //日期格式
    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");


   /**
    *@Author:      ykbian
    *@date_time:   2018/10/24 13:41
    *@Description:  重写Printable 的print()方法
    *@param:      Graphic指明打印的图形环境
    *             PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英寸的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
    *             pageIndex指明页号
   */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;
        //转换成Graphics2D
        Graphics2D g2 = (Graphics2D) graphics;
        //设置打印颜色为黑色
        g2.setColor(Color.BLACK);
        //打印起点坐标
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();
        switch (pageIndex) {
            case 0:
                //设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
                //Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
                Font font = new Font("新宋体", Font.PLAIN, 20);
                //设置字体
                g2.setFont(font);
                float[] dash1 = {2.0f};
                //设置打印线的属性。
                //1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
                g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));

                //获取需要打印的图片，若是动态生成，直接传入绝对路径即可
                Image src = Toolkit.getDefaultToolkit().getImage(STUDENT_LIST.get(COUNT).getQrCodePath());
                System.out.println(STUDENT_LIST.get(COUNT).getQrCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }
                // 图片的坐标（x,y）宽，高
                g2.drawImage(src,(int)120,(int)150,(int)136,(int)220,c);
                //标题，固定不变
                g2.drawString(STUDENT_LIST.get(COUNT).getTitle(), (float) 250, (float)100);
                //以下为动态的文字内容
                font = new Font("新宋体", Font.ROMAN_BASELINE, 15);
                g2.setFont(font);
                g2.drawString("姓名：" + STUDENT_LIST.get(COUNT).getUserName(), (float) 50, (float) 150);
                g2.drawString("性别：" + STUDENT_LIST.get(COUNT).getGender(), (float) 50, (float) 170);
                g2.drawString("民族：" + STUDENT_LIST.get(COUNT).getNation(), (float) 50, (float) 190);
                g2.drawString("住址：" + STUDENT_LIST.get(COUNT).getAddress(), (float) 50, (float) 210);
                g2.drawString("路径：" + STUDENT_LIST.get(COUNT).getQrCodePath(), (float) 50, (float) 230);
                g2.drawString("身份证号码：" + STUDENT_LIST.get(COUNT).getIDNumber(), (float) 50, (float) 250);
                g2.drawString("出生日期：" + FORMAT.format(STUDENT_LIST.get(COUNT).getBirthday()), (float) 50, (float) 280);
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }

    }

    /**
     * 封装测试数据
     *
     * @return
     */
    private static List<Persion> getStudent() {
        List<Persion> dtos = new ArrayList<>();
        dtos.add(new Persion("姜子牙", "男", "汉", "岐山县", "622222111111111111", 800, "E:\\test\\jzy.jpg", new Date(), "居民身份证"));
        dtos.add(new Persion("杨戬", "男", "汉", "岐山县", "622222111111111111", 800, "E:/test/jzy.jpg", new Date(), "居民身份证"));
        dtos.add(new Persion("哪吒", "男", "汉", "岐山县", "622222111111111111", 800, "E:/test/jzy.jpg", new Date(), "居民身份证"));
        dtos.add(new Persion("雷震子", "男", "汉", "岐山县", "622222111111111111", 800, "E:/test/jzy.jpg", new Date(), "居民身份证"));
        dtos.add(new Persion("哮天犬", "男", "汉", "岐山县", "622222111111111111", 800, "E:/test/jzy.jpg", new Date(), "居民身份证"));
//
        if (dtos.size() > 0) {
            COUNT = dtos.size() - 1;
            System.out.println("总共需打印" + (COUNT + 1) + "次");
        }
        return dtos;
    }

    public static void main(String[] args) {
        //    通俗理解就是书、文档
        Book book = new Book();
        //    设置成竖打（）
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        //    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        //纸张大小
        p.setSize(1000, 618);
        // 页面可打印的区域大小（左上角定位，参数分别是x轴，y轴，宽度和长度）
        p.setImageableArea(15, 15, 800, 600);
        pf.setPaper(p);
        //    把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new PrintUtil(), pf);
        //获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        // 设置打印类
        job.setPageable(book);
        try {
            if (STUDENT_LIST.size() > 0) {
                for (int i = 0; i < STUDENT_LIST.size(); i++) {
                    job.print();
                    --COUNT;
                }
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}