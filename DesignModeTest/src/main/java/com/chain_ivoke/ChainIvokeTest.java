package com.chain_ivoke;

/**
 * description: 链式调用测试 <br>
 */
public class ChainIvokeTest {

    private String name;
    private int age;
    private String no;
    private String grade;
    private String major;

    private ChainIvokeTest() {
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        /**姓名*/
        private String name;
        /**年龄*/
        private int age;
        /** 学号*/
        private String no;
        /*** 年级*/
        private String grade;
        /*** 专业*/
        private String major;

        public Builder stuName(String name) {
            this.name = name;
            return this;
        }

        public Builder stuAge(int age) {
            this.age = age;
            return this;
        }

        public Builder stuNo(String no) {
            this.no = no;
            return this;
        }

        public Builder stuGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder stuMajor(String major) {
            this.major = major;
            return this;
        }

        public ChainIvokeTest build(){
            ChainIvokeTest chainIvoke = new ChainIvokeTest();
            chainIvoke.name = name;
            chainIvoke.age = age;
            chainIvoke.no = no;
            chainIvoke.major = major;
            chainIvoke.grade = grade;
            return  chainIvoke;
        }
    }
}
