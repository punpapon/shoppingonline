/**
 * Description: interface InterfaceAction
 * @Author: Mano.s
 * @Version: 1.0
 * @Create date: 15/02/2012
 * Changes Log
 * ----------------
 */

package com.somapait.interfaces;

import java.sql.Connection;

import com.somapait.domain.Transaction;
import com.somapait.exception.AuthorizationException;

public interface InterfaceAction {

	// From menu
	public String init() throws AuthorizationException;

	// Load combo for search page
	public void getComboForSearch(Connection conn) ;

	// Load combo for add and edit page
	public void getComboForAddEdit(Connection conn) ;

	// To search
	public String search() throws AuthorizationException;

	// To go to add page
	public String gotoAdd() throws AuthorizationException;

	// To add
	public String add() throws AuthorizationException;

	// To edit
	public String edit() throws AuthorizationException;

	// To go to edit page
	public String gotoEdit() throws AuthorizationException;

	// To go to view page
	public String gotoView() throws AuthorizationException;

	// To update Active
	public String updateActive() throws AuthorizationException;

	// To delete
	public String delete() throws AuthorizationException;

	// To export
	public String export() throws AuthorizationException;

	// To show transaction
	public void showTransaction(Transaction transaction);

}