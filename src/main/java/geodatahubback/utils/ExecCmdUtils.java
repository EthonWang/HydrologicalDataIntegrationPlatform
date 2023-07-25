package geodatahubback.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Description
 * @Auther wyjq
 * @Date 2022/6/28
 **/

@Slf4j
public class ExecCmdUtils {

    //执行python脚本
    public static int execPython(String pythonexe,String pyScriptAbsPath, List<String> argvList){
//        String a=dataResourceDir;
//        String scriptsDir= dataResourceDir + "/Scripts/"; //eg: e:\\xx\static\\myScripts\\
//        String pyPath = scriptsDir + pyScriptName+".py"; //eg: eg: e:\\xx\static\\myScripts\\test.py

        StringBuilder cmdString=new StringBuilder(pythonexe);
        cmdString.append(" ");
        cmdString.append(pyScriptAbsPath); //eg: python eg: e:\\xx\static\\myScripts\\test.py
        for (int i=0;i<argvList.size();i++){
//            cmdString+=" "+argvList.get(i);
            cmdString.append(" ");
            cmdString.append(argvList.get(i));
        }

        try {
            log.info("开始执行脚本："+pyScriptAbsPath);
            log.info("命令："+cmdString.toString());

            Process proc = Runtime.getRuntime().exec(cmdString.toString());// 执行py文件

            //获取错误输入流
            BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String errLine = null;
            while ((errLine = err.readLine()) != null) {
                log.error("errStream: "+errLine);
            }
            err.close();

            //获取正常输入流
            //proc.getInputStream()是字节流
            //InputStreamReader 把字节流转换成字符流，用指定的编码
            //BufferedReader 把内容放到缓冲区,缓冲区中的内容可以更改，可以提高效率。
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
            String inLine = null;
            while ((inLine = in.readLine()) != null) {
                log.info("inputStream: "+inLine);
            }
            in.close();

            int exitVal =proc.waitFor(); //返回值如果是0表示正常结束

            return exitVal;
        } catch (Exception e) {
            System.out.println("脚本执行失败："+e.getMessage());
            return -1;
        }
    }
}
