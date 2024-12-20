package br.com.edu.jet.foodfusion.client.shared.dto.inventory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import br.com.edu.jet.foodfusion.client.shared.enums.GoodsCategoryEnum;

public class GoodsDTO {

    private long id;

    private String name;

    private String description;

    private String maker;

    private String model;

    private BigDecimal unitPrice;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private String EAN_8;

    private String EAN_13;

    private String EAN_14;

    private String EAN_128;

    private String SKU;

    private String UPC;

    private String UPC_A;

    private String UPC_E;

    private String GTIN;

    private String NCM;

    private String barcode;

    private List<String> patents;

    private List<String> certifications;

    private String technicalSpecs;

    private String ingredients;

    private GoodsCategoryEnum category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getEAN_8() {
        return EAN_8;
    }

    public void setEAN_8(String EAN_8) {
        this.EAN_8 = EAN_8;
    }

    public String getEAN_13() {
        return EAN_13;
    }

    public void setEAN_13(String EAN_13) {
        this.EAN_13 = EAN_13;
    }

    public String getEAN_14() {
        return EAN_14;
    }

    public void setEAN_14(String EAN_14) {
        this.EAN_14 = EAN_14;
    }

    public String getEAN_128() {
        return EAN_128;
    }

    public void setEAN_128(String EAN_128) {
        this.EAN_128 = EAN_128;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public String getUPC_A() {
        return UPC_A;
    }

    public void setUPC_A(String UPC_A) {
        this.UPC_A = UPC_A;
    }

    public String getUPC_E() {
        return UPC_E;
    }

    public void setUPC_E(String UPC_E) {
        this.UPC_E = UPC_E;
    }

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
    }

    public String getNCM() {
        return NCM;
    }

    public void setNCM(String NCM) {
        this.NCM = NCM;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<String> getPatents() {
        return patents;
    }

    public void setPatents(List<String> patents) {
        this.patents = patents;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public String getTechnicalSpecs() {
        return technicalSpecs;
    }

    public void setTechnicalSpecs(String technicalSpecs) {
        this.technicalSpecs = technicalSpecs;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public GoodsCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(GoodsCategoryEnum category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodsDTO)) return false;
        GoodsDTO goodsDTO = (GoodsDTO) o;
        return id == goodsDTO.id && deleted == goodsDTO.deleted && Objects.equals(name, goodsDTO.name) && Objects.equals(description, goodsDTO.description) && Objects.equals(maker, goodsDTO.maker) && Objects.equals(model, goodsDTO.model) && Objects.equals(unitPrice, goodsDTO.unitPrice) && Objects.equals(grossWeight, goodsDTO.grossWeight) && Objects.equals(netWeight, goodsDTO.netWeight) && Objects.equals(EAN_8, goodsDTO.EAN_8) && Objects.equals(EAN_13, goodsDTO.EAN_13) && Objects.equals(EAN_14, goodsDTO.EAN_14) && Objects.equals(EAN_128, goodsDTO.EAN_128) && Objects.equals(SKU, goodsDTO.SKU) && Objects.equals(UPC, goodsDTO.UPC) && Objects.equals(UPC_A, goodsDTO.UPC_A) && Objects.equals(UPC_E, goodsDTO.UPC_E) && Objects.equals(GTIN, goodsDTO.GTIN) && Objects.equals(NCM, goodsDTO.NCM) && Objects.equals(barcode, goodsDTO.barcode) && Objects.equals(patents, goodsDTO.patents) && Objects.equals(certifications, goodsDTO.certifications) && Objects.equals(technicalSpecs, goodsDTO.technicalSpecs) && Objects.equals(ingredients, goodsDTO.ingredients) && category == goodsDTO.category && Objects.equals(createdAt, goodsDTO.createdAt) && Objects.equals(updatedAt, goodsDTO.updatedAt) && Objects.equals(deletedAt, goodsDTO.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, maker, model, unitPrice, grossWeight, netWeight, EAN_8, EAN_13, EAN_14, EAN_128, SKU, UPC, UPC_A, UPC_E, GTIN, NCM, barcode, patents, certifications, technicalSpecs, ingredients, category, createdAt, updatedAt, deletedAt, deleted);
    }

    @Override
    public String toString() {
        return "GoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", unitPrice=" + unitPrice +
                ", grossWeight=" + grossWeight +
                ", netWeight=" + netWeight +
                ", EAN_8='" + EAN_8 + '\'' +
                ", EAN_13='" + EAN_13 + '\'' +
                ", EAN_14='" + EAN_14 + '\'' +
                ", EAN_128='" + EAN_128 + '\'' +
                ", SKU='" + SKU + '\'' +
                ", UPC='" + UPC + '\'' +
                ", UPC_A='" + UPC_A + '\'' +
                ", UPC_E='" + UPC_E + '\'' +
                ", GTIN='" + GTIN + '\'' +
                ", NCM='" + NCM + '\'' +
                ", barcode='" + barcode + '\'' +
                ", patents=" + patents +
                ", certifications=" + certifications +
                ", technicalSpecs='" + technicalSpecs + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", deleted=" + deleted +
                '}';
    }
}
