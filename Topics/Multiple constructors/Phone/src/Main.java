class Phone {

    public Phone(String ownerName, String number) {
        this.ownerName = ownerName;
        this.number = number;
    }

    public Phone(String ownerName, String countryCode, String cityCode, String number) {
        this(ownerName, number);
        this.countryCode = countryCode;
        this.cityCode = cityCode;
    }

    String ownerName;
    String countryCode;
    String cityCode;
    String number;
}