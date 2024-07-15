
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
