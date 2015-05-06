/*
 * Copyright BLC IT Services Ltd 2015
 */
package uk.co.blc_services.smartgenie.domain;

/**
 * Represents the source of a change, ie. what interface was the user
 * using when they made a change.
 * 
 * @author dave.clarke@blc-services.co.uk
 *
 */
public enum ChangeInterface {
	WEB, MOBILE_WEB, REST, EMAIL, DB;
}
