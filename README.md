
## Page周りの疑問

### ByteBuffer.allocateとallocateDirectの違い
  * allocate...ヒープに当てる、GCが発生する
  * Direct...ヒープ外に(ネイティブメモリ)に当てる、GCが効かないので手動で解放する　Pageクラスはこっち
  * 宣言する時にインスタンス化されるByteBufferの実装が違うっぽい

### putIntとputって何が違うんだっけ....

```Pageクラス
  /**
  * ページの特定の場所にbytearray型を配置する
  * @param offset
  * @param b
    */
    public void setBytes(int offset, byte[] b) {
    bb.position(offset);
    bb.putInt(b.length);
    bb.put(b);
    }
```

* put...現在の位置にバイトまたはバイト配列を入れる
* putInt...入れた整数をIntに変換して設置。格納場所としては4byte分利用される
  * int型は4byte(=32bit, -2^31 ~ 2^31 -1 = -2,147,483,648 ~ 2,147,483,647 )
  * 逆にいうとgetInt()は絶対整数の4を返す

```dtd
ByteBuffer bb = ByteBuffer.allocate(10);
bb.putInt(12345);   // 4バイトの整数を格納
```

* MEMO:setBytesしたものをgetIntしようとすると不整合が発生する気がするけど、それはいいんだっけ。。 →ようわからんけど一旦おいておく

### FileMgr

#### read,write
* readはOSのFileシステム(=クラスで言うとRandomeAccessFile、いわばファイル、もっと言うとOSが持ってるディスク??)からByteBuffer(=クラスで言うとPage、言わばメモリ)に値を読み込んできてくれる。なお事前に決まったブロック単位の模様
* 書籍からの訳：Its 'read' method seeks to the appropriate position in the specifiead file and reads the contents of that block to the byte buffer of the specified page/
（その'read'メソッドは、指定されたファイル内の適切な位置をシークし、そのブロックの内容を指定されたページ/のバイト バッファーに読み取ります。）
* writeはその逆

#### append
* RandomAccessFileの最後に空のByte配列(サイズは事前に決まったBlockSize)を突っ込むだけ
* OSに自動的にファイルを拡張させるため(causes the OS to automatically extend the file)って書いてたけど今いち用途がようわからん
