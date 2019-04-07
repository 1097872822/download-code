package com.lzw.tiles;

import java.util.List;

public class OrderManageTilesController extends MemberTilesController {
	protected List getDate() {
		return getDao().getOrders();
	}
}
