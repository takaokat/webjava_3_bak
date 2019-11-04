package jp.co.systena.tigerscave.kadai1.application.service;

import java.util.HashMap;
import java.util.Map;
import jp.co.systena.tigerscave.kadai1.application.model.Item;

public class ListService {
  Map<Integer, Item> itemList = new HashMap<>();

  public Map getItemList() {
    Item item1 = new Item(1,"キーボード",1000);
    Item item2 = new Item(2,"ノートパソコン",100000);
    Item item3 = new Item(3,"マウス",300);

    itemList.put(1, item1);
    itemList.put(2, item2);
    itemList.put(3, item3);

    return itemList;
  }


}