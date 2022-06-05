package com.jp.bettingapp.helper;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jp.bettingapp.model.Game;

import java.util.ArrayList;

public class Constant {
    public static final String MainBaseUrl = "http://bigbillionenterprises.co.in/";
    //public static final String MainBaseUrl = "http://192.168.43.38/bigbillion/";
    public static final String BaseUrl = MainBaseUrl + "api/";
    public static final String SIGNUP_USER_URL = BaseUrl + "signup_user.php";
    public static final String LOGIN_URL = BaseUrl + "login.php";
    public static final String UPDATE_USER_URL = BaseUrl + "updateuser.php";
    public static final String GAME_URL = BaseUrl + "game.php";
    public static final String MYUSER_URL = BaseUrl + "myuser.php";
    public static final String HARUF_URL = BaseUrl + "haruf.php";
    public static final String BIDSLIST_URL = BaseUrl + "bidslist.php";
    public static final String HARUFBIDSLIST_URL = BaseUrl + "harufbidslist.php";
    public static final String RESULT_LISTS_URL = BaseUrl + "resultslists.php";
    public static final String ADD_POINTS_URL = BaseUrl + "addpoints.php";
    public static final String WITHDRAWAL_URL = BaseUrl + "withdrawal.php";
    public static final String DELETE_BIDS_URL = BaseUrl + "deletebid.php";
    public static final String SHAREPOINTS_URL = BaseUrl + "sharepoints.php";
    public static final String TRANSLISTS_URL = BaseUrl + "translists.php";
    public static final String WITHDRAWALLISTS_URL = BaseUrl + "withdrawallists.php";
    public static final String UPDATE_ACCOUNT_DETAILS_URL = BaseUrl +  "update_account_details.php";
    public static final String MOBILE = "mobile";
    public static final String USER_ID = "user_id";
    public static final String AMOUNT = "amount";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String GAME_NAME = "game_name";
    public static final String GAME_TYPE = "game_type";
    public static final String DATE = "date";
    public static final String GAME_DATE = "game_date";
    public static final String GAME_METHOD = "game_method";
    public static final String NUMBER = "number";
    public static final String POINTS = "points";
    public static final String TOTAL_POINTS = "total_points";
    public static final String Login = "login";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EARN = "earn";
    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";
    public static final String ACCOUNT_NO = "account_number";
    public static final String IFSC_CODE = "ifsc_code";
    public static final String HOLDER_NAME = "holder_name";
    public static final String PAYTM = "paytm";
    public static final String PHONEPE = "phonepe";


}