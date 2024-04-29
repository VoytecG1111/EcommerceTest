get Products = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

createProductHtmlEl = (productData) => {
    const newEl = document.createElement("li");
    newEl.innerHTML = "abc xyz";
    return newEl;

}

document.addEventListener("DOMContentLoaded", () => {
    console.log("it works");
    const productList = document.querrySelector("#productList");
    getProducts()
        .then(products => products.map(createProductHtmlEl))
        .then(productsHtmls => {
            productsHtmls.forEach(htmlEl => productsList.appendChild(htmlEl))
        });

});