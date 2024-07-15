package mypractice;

import simpledb.file.Page;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {

        Page page = new Page(20);  // バッファサイズが20のPageを作成
        byte[] data = {0x01, 0x02, 0x03};  // 適当なバイト配列を定義

        page.setBytes(10, data);  // 10番目の位置にバイト配列を格納

        int intValue = page.getInt(10);  // 10番目の位置から整数を取得しようとする
        System.out.println(intValue);
    }
}