import android.os.Environment;
import org.junit.Test;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:Qiutianzhu
 * @Date 2021-12-24 09:45
 * @Description: 处理文件动作工具类
 */
public class FileCopy {


    /**
     * @Author:Qiutianzhu
     * @Description: 浏览一个文件夹的所有文件，复制到另一个文件中
     * @Date: 2021/12/24 9:50
     **/
    @Test
    public void filePathCopyFile(){
        //安卓/data/com.ten/Tencent/MobileQQ/shortvdeio
        long start = System.currentTimeMillis();
        String sourceFilePath = "F:\\shortvideo\\shortvideo";
        String targetFilePath = "F:\\copyFile";
        copyFile(sourceFilePath, targetFilePath);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * @Author:Qiutianzhu
     * @Description: 处理文件逻辑
     * @Date: 2021/12/24 10:04
     * @param sourceFilePath: 源文件路径
     * @param targetFilePath:  目标文件路径
     **/
    private void copyFile(String sourceFilePath, String targetFilePath) {
        try{
            //1.获取浏览的文件夹路径
            File sourceFile = new File(sourceFilePath);
            //2.获取复制到目标路径
            File targetFile = new File(targetFilePath);
            //源文件夹路径是否存在
            if (sourceFile.exists()){
                if (!targetFile.exists()){
                    //目标路径不存在，直接创建
                    targetFile.mkdirs();
                }
                //3.拿到所有的文件夹集合，对象
                File[] sourceItem = sourceFile.listFiles();
                //4.挨个遍历
                for (File item : sourceItem){
                    //5.判断是否文件夹还是文件
                    if (item.isFile()){
                        if (item.getName().endsWith(".mp4")){
                            //7.文件获取时间信息
                            String time = loadFileTime(item);
                            //8.对应时间信息复制到确定路径下的日期分区
                            File targetFileFolder = new File(targetFilePath + File.separator + time);
                            //拼接的日期文件是否存在
                            if (!targetFileFolder.exists()){
                                //目标路径不存在，直接创建
                                targetFileFolder.mkdirs();
                            }
                            //在复制文件到文件夹下
                            String targetFilePathSplicing = targetFilePath + File.separator + time + File.separator + item.getName();
                            copySourceInTarget(item, new File(targetFilePathSplicing));
                        }
                    }else {
                        //6.文件夹继续往下找
                        copyFile(item.getPath(), targetFilePath);
                    }
                }
            }else {
                System.out.println("source path:"+ sourceFile +" is not exists!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * @Author:Qiutianzhu
     * @Description: 复制文件到指定路径
     * @Date: 2021/12/24 14:33
     * @param source: 源文件路径
     * @param target:  目标文件路径
     **/
    private void copySourceInTarget(File source, File target) throws Exception{
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            IOUtils.copyLarge(fis, fos);
        } catch (Exception var16) {
            throw var16;
        } finally {
            fis.close();
            fos.close();
        }
        /*FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel targetChannel = new FileOutputStream(target).getChannel();
        sourceChannel.transferTo(0,sourceChannel.size(),targetChannel);
        sourceChannel.close();
        targetChannel.close();*/
    }

    /**
     * @Author:Qiutianzhu
     * @Description: 根据文件对象获取文件的创建时间
     * @Date: 2021/12/24 10:17
     * @param item:  文件对象
     * @return: java.lang.String 返回日期字符串 2021-12-24
     **/
    private String loadFileTime(File item) {
        String time = "";
        try{
            BasicFileAttributes attributes =  Files.readAttributes(Paths.get(item.getPath()), BasicFileAttributes.class);
            FileTime fileTime = attributes.lastModifiedTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            time = simpleDateFormat.format(new Date(fileTime.toMillis()));
        }catch (Exception e){
            e.getMessage();
        }
        return time;
    }

}
