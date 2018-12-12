package com.auts.ljtmanager.service;

import java.util.List;

import com.auts.ljtmanager.model.common.PageInfo;
import com.auts.ljtmanager.model.dao.product.ProductAttachmentModel;
import com.auts.ljtmanager.model.dao.product.ProductModel;
import com.auts.ljtmanager.model.dao.product.ProfitRebateModel;

public interface ProductsService {

	int addProduct(ProductModel productModel);

	int updateProducts(ProductModel productModel);

	PageInfo queryProductList(int pageNo, int pageSize, String pType);

    int queryProductCountByPType(String type);

    ProductModel queryProductDetail(String pCode);

    List<ProductModel> queryRecommendProducts(String recommendype);

    List<ProfitRebateModel> queryProfitRebateByPCode(String pCode);

    List<ProductAttachmentModel> queryProductAttachmentByPCode(String pCode);
}
