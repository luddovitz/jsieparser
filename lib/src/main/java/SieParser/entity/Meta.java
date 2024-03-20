package SieParser.entity;

public class Meta extends Entity {

    private String companyName;
    private String organizationNumber;
    private String sieType;

    public Meta() {}

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrganizationNumber() {
        return organizationNumber;
    }

    public void setOrganizationNumber(String organizationNumber) {
        this.organizationNumber = organizationNumber;
    }

    public String getSieType() {
        return sieType;
    }

    public void setSieType(String sieType) {
        this.sieType = sieType;
    }
}
