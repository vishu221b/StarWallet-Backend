package com.StarWallet.StarWallet.enums;

public enum UserType {
    MERCHANT(1),
    CUSTOMER(2);

    private final int Id;

    public static UserType getById(int id){
        for(UserType u: UserType.values()){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    public int getId() {
        return Id;
    }

    UserType(int typeId){
        this.Id=typeId;
    }
}
