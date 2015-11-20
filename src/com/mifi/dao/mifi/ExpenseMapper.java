package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.Expense;

public interface ExpenseMapper {

//	Expense getExpense();
	List<Expense> selectExpense();
}
