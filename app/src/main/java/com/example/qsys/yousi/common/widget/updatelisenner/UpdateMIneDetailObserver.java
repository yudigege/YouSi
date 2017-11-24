package com.example.qsys.yousi.common.widget.updatelisenner;

import java.util.ArrayList;

/**
 * @author hanshaokai
 * @date 2017/11/24 16:57
 */


public class UpdateMIneDetailObserver {
    public static ArrayList<UpdateMineDetailLisener> baseFragments=new ArrayList<>();

    public static void setListener(UpdateMineDetailLisener updateMineDetailLisener){
        baseFragments.add(updateMineDetailLisener);
    }
 public static void clearChache(){
     baseFragments.clear();
 }

 public  interface  UpdateMineDetailLisener{
      void updateMineDetail(String nickName);
  }
}
