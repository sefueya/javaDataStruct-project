package com.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName: GreedyAlgorithm
 * Package: com.greedy
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/5 9:24
 * @Version 1.0
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建电台集合
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        // 准备电台覆盖的地区 hashset
        HashSet<String> hashset1 = new HashSet<>();
        hashset1.add("北京");
        hashset1.add("上海");
        hashset1.add("天津");

        HashSet<String> hashset2 = new HashSet<>();
        hashset2.add("广州");
        hashset2.add("北京");
        hashset2.add("深圳");

        HashSet<String> hashset3 = new HashSet<>();
        hashset3.add("成都");
        hashset3.add("上海");
        hashset3.add("杭州");

        HashSet<String> hashset4 = new HashSet<>();
        hashset4.add("上海");
        hashset4.add("天津");

        HashSet<String> hashset5 = new HashSet<>();
        hashset5.add("杭州");
        hashset5.add("大连");

        // 加入到 broadcasat HashMap
        broadcasts.put("K1",hashset1);
        broadcasts.put("K2",hashset2);
        broadcasts.put("K3",hashset3);
        broadcasts.put("K4",hashset4);
        broadcasts.put("K5",hashset5);

        // 存放所有需要 覆盖的地区 allArea  HashSet
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建ArrayList，存放选的电台
        List<String> selects = new ArrayList<>();

        // 定义一个临时的集合，在遍历的过程中，存放遍历过程中的 当前电台覆盖的地区 和 当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // 定义maxKey，保存在一轮遍历过程中，能够覆盖最多 未覆盖地区 对应的电台的key
        String maxKey = null;

        while(!allAreas.isEmpty()){

            // 遍历broadcast，取出对应的key
            for(String key:broadcasts.keySet()){

                // tempSet 重新置为空
                tempSet.clear();

                // 当前这个key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);

                tempSet.addAll(areas);

                // boolean retainAll(Collection coll)	当前集合中删除两个集合中不同的元素
                // 求出tempSet 和 allAreas集合的交集，交集会 赋给 tempSet
                tempSet.retainAll(allAreas);


                // 以下if体现出 贪婪算法的特点，每一次都选最好的
                // 如果当前 key覆盖的未覆盖地区 比 maxKey覆盖的未覆盖地区地区更多，那么maxKey变成新的key
                if(!tempSet.isEmpty() && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }

            if(maxKey != null){
                selects.add(maxKey);
                // 将maxKey覆盖的地区 从 allAreas 中去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }

            // maxKey
            maxKey = null;
        }

        System.out.println("得到的选择结果是:" + selects); // 得到的选择结果是:[K1, K2, K3, K5]
    }
}
