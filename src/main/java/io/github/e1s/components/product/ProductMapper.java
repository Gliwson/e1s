package io.github.e1s.components.product;

class ProductMapper {

    static ProductDTO productToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        if (product == null) {
            return null;
        } else {
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setProductType(product.getProductType());
        }
        return productDTO;
    }
}
