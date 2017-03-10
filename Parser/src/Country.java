public class Country {
    private String name;
    private String region;
    private String incomeLevel;
    private String iso2code;
    private Double lifeExpectancy;

    public Country(String name,String iso2code) {
        //Complete the constructor
        this.name = name;
        this.iso2code = iso2code;

    }

    public String getName() {
        return name;
    }

    public String getIso2Code() {
        return iso2code;
    }

    public String getRegion() { return this.region; }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;

    }

    public void setLifeExpectancy(Double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getReportRow() {
        if (region == null) region = " - ";
        if (incomeLevel == null) incomeLevel = " - ";
        if (lifeExpectancy != null)
            return name + ", " + region + ", " + incomeLevel + ", " + lifeExpectancy + "\n";
        else
            return name + ", " + region + ", " + incomeLevel + ", " + " - " + "\n";

    }
}