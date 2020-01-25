package com.designpattern.structural_pattern.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式可以使用户一致性地处理整个树形结构或树形结构地一部分，也可以一致性地处理树形结构中的叶子节点
 * 和容器节点。
 *
 * 该模式为处理树形结构提供了一种较为完美的解决方案，描述了如何将容器和叶子进行递归组合。使得用户在使用的
 * 时候无须对它们进行区分。可以一致性地对待容器和叶子。
 *
 * 组合模式的三个角色，抽象构件、叶子构件和容器构件
 *
 *
 *
 * 组合模式有两种细分，(1)透明模式，如下代码，同时还可以将叶子构件中的add()、delete()等管理方法删除。
 * 但是注意创建文件结构声明叶子构件的时候不能多态声明。
 * (2)安全模式，将add()、delete()等方法移入容器类，相应的此时容器类声明时相对于抽象构件不能做到透明。
 */
public class BaseCodeFormAndClient {
    //这里是透明模式

    /**
     *  抽象构件
     */
    public abstract  class AbstractFile{
        private String name;
        public AbstractFile(String name){
            this.name=name;
        }

        //实际的操作方法
        public abstract void killVirus();
        //管理方法
        public abstract void add(AbstractFile file);
        public abstract void delete(AbstractFile file);
    }

    /**
     * 容器构件
     */
    public class Folder extends AbstractFile{
        public Folder(String name){
            super(name);
        }
        //需要维护一个容器集合(利用多态)
        List<AbstractFile> fileList = new ArrayList<AbstractFile>();

        @Override
        public void killVirus() {
                for(AbstractFile file:fileList){
                    file.killVirus();
                }
        }

        @Override
        public void add(AbstractFile file) {
            fileList.add(file);
        }

        @Override
        public void delete(AbstractFile file) {
            fileList.remove(file);
        }
    }

    /**
     * 叶子构件——文本文件
     */
    public class TextFile extends AbstractFile{

        public TextFile(String name) {
            super(name);
        }

        @Override
        public void killVirus() {
            System.out.println("对文本文件进行杀毒");
        }

        @Override
        public void add(AbstractFile file) {
            System.out.println("文本文件不支持该操作");
        }

        @Override
        public void delete(AbstractFile file) {
            System.out.println("文本文件不支持该操作");
        }
    }

    /**
     * 叶子构件——图像文件
     */
    public class ImageFile extends AbstractFile{

        public ImageFile(String name) {
            super(name);
        }

        @Override
        public void killVirus() {
            System.out.println("对图像文件进行杀毒");
        }

        @Override
        public void add(AbstractFile file) {
            System.out.println("图像文件不支持该操作");
        }

        @Override
        public void delete(AbstractFile file) {
            System.out.println("图像文件不支持该操作");
        }
    }

    public class VideoFile extends AbstractFile{

        public VideoFile(String name) {
            super(name);
        }

        @Override
        public void killVirus() {
            System.out.println("对影像文件进行杀毒");
        }

        @Override
        public void add(AbstractFile file) {
            System.out.println("影像文件不支持该操作");
        }

        @Override
        public void delete(AbstractFile file) {
            System.out.println("影像文件不支持该操作");
        }
    }

    public static void main(String[] args) {
        AbstractFile file1,file2,file3,file4,file5,folder1,folder2,folder3,folder4;

        folder1 =new BaseCodeFormAndClient().new Folder("liuchao的总文件夹");
        folder2=new BaseCodeFormAndClient().new Folder("liuchao的文本文件夹");
        folder3=new BaseCodeFormAndClient().new Folder("liuchao的图像文件夹");
        folder4=new BaseCodeFormAndClient().new Folder("liuchao的影像文件夹");

        file1=new BaseCodeFormAndClient().new TextFile("《三国演义》");
        file2=new BaseCodeFormAndClient().new ImageFile("《星空》");
        file3=new BaseCodeFormAndClient().new ImageFile("《池边少女》");
        file4=new BaseCodeFormAndClient().new VideoFile("《心灵捕手》");
        file5=new BaseCodeFormAndClient().new TextFile("《红楼梦》");

        //进行文件结构的建立
        folder1.add(folder2);
        folder1.add(folder3);
        folder1.add(folder4);
        folder2.add(file1);
        folder2.add(file5);
        folder3.add(file2);
        folder3.add(file3);
        folder4.add(file4);

        //从总文件层开始杀毒。
        folder1.killVirus();
    }
}
