package com.wei.service;

import com.github.pagehelper.PageInfo;
import com.wei.domain.PromotionAd;
import com.wei.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {

    /**
     * 分页查询广告信息
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /**
     * 新建广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息（回显）
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 广告动态上下线
     */
    public void updatePromotionAdStatus(int id, int status);
}
