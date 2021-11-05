package bo;

import bo.custom.Impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public  static  BOFactory getBoFactory(){

        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public  enum BOTypes{
        ADMIN_HOME, ANNUALLY_INCOME, CUSTOMER, CUSTOMER_WISE_INCOME, ITEM, LESS_MOVABLE_ITEM, LOGIN,
        MONTHLY_INCOME, MOST_MOVABLE_ITEM, ORDER, ORDER_MANAGE, USER_ACCOUNT

    }


    public SuperBO getBO(BOTypes types){
        switch (types){
            case ADMIN_HOME:
                return new AdminHomeBOImpl();
            case ANNUALLY_INCOME:
                return new AnnuallyIncomeBOImpl();
            case CUSTOMER:
                return  new CustomerBOImpl();
            case CUSTOMER_WISE_INCOME:
                return new CustomerWiseBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case LESS_MOVABLE_ITEM:
                return new LessMovableBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MONTHLY_INCOME:
                return new MonthlyIncomeBOImpl();
            case MOST_MOVABLE_ITEM:
                return new MostMovableBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_MANAGE:
                return new OrderManageBOImpl();
            case USER_ACCOUNT:
                return new UserAccountBOImpl();
            default:
                return  null;

        }
    }

    



}
