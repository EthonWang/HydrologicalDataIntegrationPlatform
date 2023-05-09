package geodatahubback.utils;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/4/4
 **/
@Slf4j
public class MyFileUtil {
    public static void downloadFile(File file, HttpServletResponse response) {
        OutputStream os = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            //解决页面下载框中文乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));

            byte[] readBytes = FileUtil.readBytes(file);

            os = response.getOutputStream();
            os.write(readBytes);
            os.close();
        } catch (Exception e) {
            log.error("文件下载失败");
            e.printStackTrace();
        }
    }

    public static void downloadFilePro(File file, HttpServletResponse res) {
        try {
            res.reset();
            res.setContentType("application/octet-stream");
            res.setCharacterEncoding("utf-8");
            res.setContentLength((int) file.length());
            //解决页面下载框中文乱码问题
            res.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream os = res.getOutputStream();

            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            bis.close();
            os.close();

        } catch (Exception e) {
            log.error("文件下载失败");
            e.printStackTrace();
        }
    }

    public void getNetFile(String netAddress, String path) {
        try {
            URL url = new URL(netAddress);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));

            int len;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            is.close();
            bos.close();
        } catch (Exception e) {
            log.error("文件下载失败");
            e.printStackTrace();
        }

    }

    public void downLoadNetFile(String netAddress, String filename, boolean isOnLine, HttpServletResponse response) {
        try {
            URL url = new URL(netAddress);
            URLConnection conn = url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

            response.reset();
            response.setContentType(conn.getContentType());
            if (isOnLine) {
                // 在线打开方式 文件名应该编码成UTF-8
                response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(filename, "UTF-8"));
            } else {
                //纯下载方式 文件名应该编码成UTF-8
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            }

            byte[] buffer = new byte[1024];
            int len;
            ServletOutputStream os = response.getOutputStream();
            while ((len = bis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            bis.close();

        } catch (Exception e) {
            log.error("文件下载失败");
            e.printStackTrace();
        }
    }
}
