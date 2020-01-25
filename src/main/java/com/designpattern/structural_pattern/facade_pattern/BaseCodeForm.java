package com.designpattern.structural_pattern.facade_pattern;

/**
 * 外观模式要求一个子系统的外部与其内部的通信通过一个统一的外观角色进行，外观角色将客户端与子系统的
 * 内部复杂性分隔开，使得客户端只需要与外观角色打交道，而不需要与子系统内部的很多对象打交道。
 *
 * 外观模式有两个角色，外观角色和子系统角色
 *
 * 外观模式并不给系统增加任何新功能，它仅仅是简化调用接口
 * */
public class BaseCodeForm {
    //这里直接演示抽象外观模式的代码

    /**
     * 抽象外观类
     */
    abstract class AbstractFacade{
        public abstract void fileEncrypt(String fileName,String pathName);
    }


    /**
     * 外观类A
     */
    class EncryptFacadeA extends AbstractFacade{
        FileReader reader=new FileReader();
        CipherMachineA cipher =new CipherMachineA();
        FileWrite writer =new FileWrite();



        @Override
        public void fileEncrypt(String fileName, String pathName) {
            reader.read(fileName,pathName);
            cipher.encrypt(fileName);
            writer.write(fileName);
        }
    }

    /**
     * 外观类B
     */
    class EncryptFacOadeB extends AbstractFacade{
        FileReader reader=new FileReader();
        CipherMachineB cipher =new CipherMachineB();
        FileWrite writer =new FileWrite();

        @Override
        public void fileEncrypt(String fileName, String pathName) {
            reader.read(fileName,pathName);
            cipher.encrypt(fileName);
            writer.write(fileName);
        }
    }

    //如下是子系统角色
    /**
     * 文件读入类
     */
     class FileReader{
        public void read(String fileName,String pathName){
            System.out.println("从"+pathName+"读入文件:"+fileName);
        }
    }
    class CipherMachineA{
         public void encrypt(String fileName){
             System.out.println("对文件"+fileName+"使用方法A进行解密");
         }
    }
    class CipherMachineB{
         public void encrypt(String fileName){
             System.out.println("对文件"+fileName+"使用方法B进行解密");
         }
    }
    class FileWrite{
         public void write(String fileName){
             System.out.println(fileName+"文件解密出的内容为——");
         }
    }
}
