package com.example.coffeeshopapp.data.source

import com.example.coffeeshopapp.data.model.CoffeeCategory
import com.example.coffeeshopapp.data.model.Product

fun ProductsData(): List<Product> {
    return listOf(
        // ===================== CAPPUCCINO =====================
        Product(
            id = 1,
            name = "Classic Cappuccino",
            subtitle = "Espresso with milk foam",
            description =
            "Cappuccino made with fresh espresso, steamed milk, and thick milk foam on top. " +
                    "Balanced coffee flavor delivers smooth and rich taste. " +
                    "Creamy foam adds light texture and pleasant mouthfeel. " +
                    "Perfect choice for calm and relaxed mornings. " +
                    "Best served warm with breakfast pastries.",
            price = 350.0,
            rating = 4.5,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://images.unsplash.com/photo-1683442032849-9788d3828053?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Q2xhc3NpYyUyMENhcHB1Y2Npbm98ZW58MHx8MHx8fDA%3D"
        ),

        Product(
            id = 2,
            name = "Vanilla Cappuccino",
            subtitle = "Cappuccino with vanilla flavor",
            description =
            "Cappuccino prepared with espresso, milk foam, and smooth vanilla syrup. " +
                    "Sweet vanilla aroma blends gently with coffee flavor. " +
                    "Creamy texture creates comforting drinking experience. " +
                    "Ideal for flavored coffee lovers. " +
                    "Perfect for cozy mornings or afternoon breaks.",
            price = 360.0,
            rating = 4.6,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://images.unsplash.com/photo-1751906030910-696a16aa670f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzF8fFZhbmlsbGElMjBDYXBwdWNjaW5vfGVufDB8fDB8fHww"
        ),

        Product(
            id = 3,
            name = "Caramel Cappuccino",
            subtitle = "Cappuccino with caramel syrup",
            description =
            "Cappuccino blended with espresso, milk foam, and caramel syrup. " +
                    "Rich caramel notes enhance coffee sweetness. " +
                    "Smooth foam delivers creamy texture. " +
                    "Great option for dessert-style coffee. " +
                    "Serve warm for best flavor balance.",
            price = 370.0,
            rating = 4.6,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://www.forkinthekitchen.com/wp-content/uploads/2022/06/220518.homemade.caramel.latte-6630.jpg"
        ),

        Product(
            id = 4,
            name = "Hazelnut Cappuccino",
            subtitle = "Nut flavored cappuccino",
            description =
            "Cappuccino infused with hazelnut syrup and rich espresso. " +
                    "Nutty aroma creates unique coffee experience. " +
                    "Creamy foam balances flavor smoothly. " +
                    "Perfect for trying new coffee tastes. " +
                    "Best enjoyed in the evening.",
            price = 375.0,
            rating = 4.7,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://www.wholesalesuppliesplus.com/cdn-cgi/image/format=auto/https://www.wholesalesuppliesplus.com/Images/Products/586-cappuccino-hazelnut.jpg"
        ),

        Product(
            id = 5,
            name = "Chocolate Cappuccino",
            subtitle = "Cappuccino with chocolate",
            description =
            "Classic cappuccino mixed with rich chocolate syrup. " +
                    "Deep cocoa flavor complements espresso taste. " +
                    "Smooth texture creates indulgent feeling. " +
                    "Perfect for chocolate coffee lovers. " +
                    "Pairs well with sweet desserts.",
            price = 380.0,
            rating = 4.7,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://thumbs.dreamstime.com/b/rich-cappuccino-topped-cocoa-powder-served-wooden-coaster-alongside-chocolate-pieces-smooth-features-frothy-top-364828698.jpg"
        ),

        Product(
            id = 6,
            name = "Cinnamon Cappuccino",
            subtitle = "Warm spiced cappuccino",
            description =
            "Cappuccino prepared with espresso and cinnamon spice. " +
                    "Warm aroma creates comforting experience. " +
                    "Balanced sweetness with light spice notes. " +
                    "Ideal for cold weather mornings. " +
                    "Best served hot for full flavor.",
            price = 360.0,
            rating = 4.5,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://nestinglane.com/wp-content/uploads/2021/12/CINNAMON-CAPPUCCINO-WEIGHT-WATCHERS-post1-500x500.jpg"
        ),

        Product(
            id = 7,
            name = "Mocha Cappuccino",
            subtitle = "Coffee with chocolate notes",
            description =
            "Cappuccino blended with espresso and chocolate flavor. " +
                    "Smooth mocha taste balances strong coffee. " +
                    "Creamy foam adds soft texture. " +
                    "Perfect afternoon drink. " +
                    "Enjoy with light snacks.",
            price = 385.0,
            rating = 4.6,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://www.acouplecooks.com/wp-content/uploads/2021/12/Mocha-Cappuccino-004.jpg"
        ),

        Product(
            id = 8,
            name = "Dark Roast Cappuccino",
            subtitle = "Bold cappuccino flavor",
            description =
            "Cappuccino made using dark roasted espresso beans. " +
                    "Strong flavor with rich aroma. " +
                    "Creamy foam balances intensity. " +
                    "Perfect for strong coffee lovers. " +
                    "Best enjoyed without sugar.",
            price = 360.0,
            rating = 4.8,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://static.vecteezy.com/system/resources/thumbnails/069/777/184/small/aromatic-coffee-cappuccino-with-latte-art-and-cookie-on-dark-background-photo.jpg"
        ),

        Product(
            id = 9,
            name = "Extra Foam Cappuccino",
            subtitle = "Foamy cappuccino style",
            description =
            "Cappuccino topped with extra thick milk foam. " +
                    "Light texture with smooth coffee base. " +
                    "Foam enhances visual presentation. " +
                    "Great for latte art lovers. " +
                    "Serve hot for best texture.",
            price = 350.0,
            rating = 4.4,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://images.ctfassets.net/0e6jqcgsrcye/6Dnzkf1ylG7IxDRG9Ez1Ia/0db4f0be1ff6199ae89afa4a0ae26687/How_to_make_a_perfect_cappuccino_at_home.jpg"
        ),

        Product(
            id = 10,
            name = "Creamy Cappuccino",
            subtitle = "Smooth and creamy coffee",
            description =
            "Creamy cappuccino prepared with rich milk and espresso. " +
                    "Soft texture creates relaxing taste. " +
                    "Balanced coffee strength. " +
                    "Suitable for any time of day. " +
                    "Enjoy warm for best experience.",
            price = 355.0,
            rating = 4.5,
            category = CoffeeCategory.CAPPUCCINO,
            imageUrl = "https://img.freepik.com/premium-photo/cup-cappuccino-coffee-with-cookies_318269-1556.jpg"
        ),
        // ===================== LATTE =====================
        Product(
            id = 11,
            name = "Classic Latte",
            subtitle = "Espresso with steamed milk",
            description =
            "Latte prepared with smooth espresso and freshly steamed milk. " +
                    "Mild coffee flavor balanced with creamy milk texture. " +
                    "Light foam creates soft and pleasant mouthfeel. " +
                    "Perfect for daily coffee routines. " +
                    "Best enjoyed warm during morning hours.",
            price = 350.0,
            rating = 4.5,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://thumbs.dreamstime.com/b/heart-latte-art-white-coffee-cup-wooden-table-warm-lighting-close-up-heart-shaped-latte-art-white-cup-423833163.jpg"
        ),

        Product(
            id = 12,
            name = "Vanilla Latte",
            subtitle = "Latte with vanilla syrup",
            description =
            "Latte made with espresso, steamed milk, and vanilla syrup. " +
                    "Sweet vanilla aroma blends smoothly with coffee taste. " +
                    "Creamy texture offers comforting experience. " +
                    "Ideal choice for flavored coffee lovers. " +
                    "Perfect for cozy mornings or afternoon breaks.",
            price = 360.0,
            rating = 4.6,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://img.freepik.com/premium-photo/cup-latte-art-wooden-background_5207-717.jpg"
        ),

        Product(
            id = 13,
            name = "Caramel Latte",
            subtitle = "Latte with caramel flavor",
            description =
            "Latte prepared with espresso, milk, and caramel syrup. " +
                    "Rich caramel sweetness enhances smooth coffee flavor. " +
                    "Creamy milk balances sweetness perfectly. " +
                    "Great option for dessert-style coffee. " +
                    "Serve warm for best taste.",
            price = 370.0,
            rating = 4.6,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://thumbs.dreamstime.com/b/caramel-drizzled-latte-coffee-beans-wooden-table-window-light-image-generated-using-ai-375592927.jpg"
        ),

        Product(
            id = 14,
            name = "Hazelnut Latte",
            subtitle = "Nut flavored latte",
            description =
            "Latte infused with hazelnut syrup and rich espresso. " +
                    "Nutty aroma adds unique coffee experience. " +
                    "Smooth texture delivers balanced flavor. " +
                    "Perfect for trying something different. " +
                    "Enjoy during relaxed evenings.",
            price = 375.0,
            rating = 4.7,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://www.shutterstock.com/image-photo/hot-hazelnut-latte-white-cup-260nw-788948107.jpg"
        ),

        Product(
            id = 15,
            name = "Mocha Latte",
            subtitle = "Latte with chocolate notes",
            description =
            "Latte blended with espresso and rich chocolate syrup. " +
                    "Smooth mocha flavor balances coffee strength. " +
                    "Creamy milk adds soft texture. " +
                    "Perfect for chocolate coffee fans. " +
                    "Pairs well with sweet snacks.",
            price = 380.0,
            rating = 4.7,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://thumbs.dreamstime.com/b/hot-mocha-leaf-latte-art-yellow-cup-marble-table-blurred-slice-layered-dessert-background-ideal-menus-cafes-cozy-392258492.jpg"
        ),

        Product(
            id = 16,
            name = "Cinnamon Latte",
            subtitle = "Warm spiced latte",
            description =
            "Latte prepared with espresso, milk, and cinnamon spice. " +
                    "Warm aroma creates comforting feeling. " +
                    "Light spice adds depth to flavor. " +
                    "Ideal for cold weather days. " +
                    "Best served hot for full aroma.",
            price = 360.0,
            rating = 4.5,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/071/455/146/non_2x/delicious-latte-art-coffee-with-cinnamon-rustic-coffee-beans-and-wooden-spoon-free-photo.jpg"
        ),

        Product(
            id = 17,
            name = "Honey Latte",
            subtitle = "Latte sweetened with honey",
            description =
            "Latte made with espresso, steamed milk, and natural honey. " +
                    "Gentle sweetness complements smooth coffee taste. " +
                    "Creamy texture creates relaxing experience. " +
                    "Perfect for light and healthy coffee choice. " +
                    "Enjoy during calm afternoons.",
            price = 365.0,
            rating = 4.6,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/048/146/806/non_2x/cappuccino-latte-with-honey-and-lavender-on-white-background-free-photo.jpg"
        ),

        Product(
            id = 18,
            name = "Almond Latte",
            subtitle = "Latte with almond milk",
            description =
            "Latte prepared using almond milk and espresso. " +
                    "Light nutty flavor with smooth texture. " +
                    "Lower dairy feel with rich taste. " +
                    "Suitable for alternative milk lovers. " +
                    "Best served warm or lightly hot.",
            price = 370.0,
            rating = 4.6,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://static.vecteezy.com/system/resources/thumbnails/072/778/058/small/almond-milk-latte-with-vanilla-beans-photo.jpg"
        ),

        Product(
            id = 19,
            name = "Vanilla Caramel Latte",
            subtitle = "Latte with vanilla and caramel",
            description =
            "Latte prepared with espresso, milk, vanilla syrup, and caramel syrup. " +
                    "Sweet and aromatic flavor with smooth finish. " +
                    "Creamy texture delivers rich coffee experience. " +
                    "Perfect for dessert lovers. " +
                    "Best enjoyed warm with cozy treats.",
            price = 390.0,
            rating = 4.7,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://coffeecopycat.com/wp-content/uploads/2024/01/CaramelLatte4-1200x1800-1.jpg"
        ),

        Product(
            id = 20,
            name = "Extra Creamy Latte",
            subtitle = "Rich milk latte",
            description =
            "Latte made with extra steamed milk for creamy texture. " +
                    "Smooth and soft coffee flavor. " +
                    "Low bitterness with balanced taste. " +
                    "Ideal for milk coffee lovers. " +
                    "Perfect for any time of day.",
            price = 355.0,
            rating = 4.5,
            category = CoffeeCategory.LATTE,
            imageUrl = "https://thumbs.dreamstime.com/b/coffee-latte-milk-cream-flower-wooden-spoon-bean-background-wood-table-beans-art-view-top-hot-barista-beautiful-tasty-bubble-105366157.jpg"
        ),
// ===================== ESPRESSO =====================
        Product(
            id = 21,
            name = "Classic Espresso",
            subtitle = "Strong and bold",
            description =
            "Espresso prepared with freshly ground beans for deep flavor. " +
                    "Rich and intense coffee taste with robust aroma. " +
                    "Small cup maintains full espresso body. " +
                    "Perfect for a quick caffeine boost. " +
                    "Enjoy straight or with a hint of sugar.",
            price = 300.0,
            rating = 4.7,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://img.freepik.com/free-photo/cup-coffee-viewed-from_1232-1336.jpg"
        ),

        Product(
            id = 22,
            name = "Double Espresso",
            subtitle = "Extra strong shot",
            description =
            "Double espresso made with 60ml of rich espresso. " +
                    "Intense coffee flavor with powerful aroma. " +
                    "Small cup emphasizes strength and body. " +
                    "Perfect for mornings needing extra kick. " +
                    "Enjoy straight or with a light snack.",
            price = 350.0,
            rating = 4.8,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://www.shutterstock.com/image-photo/cup-double-espresso-on-wooden-600nw-2527832491.jpg"
        ),

        Product(
            id = 23,
            name = "Ristretto Espresso",
            subtitle = "Short and concentrated",
            description =
            "Espresso shot with half the water, extra concentrated flavor. " +
                    "Rich aroma and intense coffee taste. " +
                    "Creamy texture despite small volume. " +
                    "Perfect for espresso lovers seeking depth. " +
                    "Best enjoyed straight without sugar.",
            price = 320.0,
            rating = 4.7,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://gospecialtycoffee.com/medialibrary/2023/03/ristretto-recipe-400x375.jpg"
        ),

        Product(
            id = 24,
            name = "Lungo Espresso",
            subtitle = "Long coffee shot",
            description =
            "Espresso shot with more water for lighter taste. " +
                    "Smooth and balanced flavor without bitterness. " +
                    "Retains espresso aroma in a larger cup. " +
                    "Great for longer coffee sessions. " +
                    "Enjoy straight or with light milk.",
            price = 310.0,
            rating = 4.6,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://sehablacafe.com/wp-content/uploads/2019/11/espresso_cup.jpg"
        ),

        Product(
            id = 25,
            name = "Iced Espresso",
            subtitle = "Chilled espresso shot",
            description =
            "Espresso poured over ice for refreshing taste. " +
                    "Strong coffee flavor remains intense and smooth. " +
                    "Light and crisp texture for hot days. " +
                    "Perfect for summer coffee breaks. " +
                    "Serve with optional sugar or milk.",
            price = 330.0,
            rating = 4.6,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://img.freepik.com/premium-photo/cup-iced-coffee-sits-wooden-table-with-coffee-beans_36078-1174.jpg"
        ),

        Product(
            id = 26,
            name = "Espresso Macchiato",
            subtitle = "Espresso with foam",
            description =
            "Classic espresso topped with a small dollop of milk foam. " +
                    "Strong coffee taste with creamy highlight. " +
                    "Perfect balance between boldness and softness. " +
                    "Ideal for short coffee breaks. " +
                    "Enjoy straight or with sweet treat.",
            price = 340.0,
            rating = 4.7,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://t3.ftcdn.net/jpg/14/16/79/16/360_F_1416791616_uenPjMLSzZygmnX1UTHKeicr1H2p5mBH.jpg"
        ),

        Product(
            id = 27,
            name = "Espresso Con Panna",
            subtitle = "Espresso with whipped cream",
            description =
            "Espresso topped with rich whipped cream for indulgence. " +
                    "Intense coffee flavor paired with creamy sweetness. " +
                    "Smooth texture enhances drinking experience. " +
                    "Perfect for dessert-style coffee lovers. " +
                    "Best served warm.",
            price = 350.0,
            rating = 4.7,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://png.pngtree.com/thumb_back/fh260/background/20221005/pngtree-red-chrysanthemum-adorned-espresso-con-panna-concept-bean-espresso-photo-image_14936476.jpg"
        ),

        Product(
            id = 28,
            name = "Espresso Romano",
            subtitle = "Espresso with lemon twist",
            description =
            "Espresso served with a slice of lemon for unique flavor. " +
                    "Citrus aroma balances strong coffee notes. " +
                    "Smooth yet tangy taste experience. " +
                    "Ideal for adventurous coffee drinkers. " +
                    "Best enjoyed fresh and warm.",
            price = 345.0,
            rating = 4.6,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://frommypantry.com/wp-content/uploads/2023/07/recipe-for-coffee-romano.jpg"
        ),

        Product(
            id = 29,
            name = "Espresso Affogato",
            subtitle = "Espresso over ice cream",
            description =
            "Classic espresso poured over a scoop of vanilla ice cream. " +
                    "Rich coffee flavor complements creamy ice cream. " +
                    "Smooth texture with indulgent dessert taste. " +
                    "Perfect for after-dinner treat. " +
                    "Serve immediately for best experience.",
            price = 380.0,
            rating = 4.8,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://thumbs.dreamstime.com/b/affogato-ice-cream-vanilla-espresso-cup-coffee-wooden-table-cafe-generative-ai-385782178.jpg"
        ),

        Product(
            id = 30,
            name = "Extra Strong Espresso",
            subtitle = "Bold and powerful",
            description =
            "Espresso made with extra ground coffee for maximum strength. " +
                    "Intense aroma and deep flavor in small cup. " +
                    "Perfect for coffee enthusiasts seeking bold taste. " +
                    "Smooth yet powerful mouthfeel. " +
                    "Enjoy straight or with a touch of sugar.",
            price = 360.0,
            rating = 4.8,
            category = CoffeeCategory.ESPRESSO,
            imageUrl = "https://img.freepik.com/free-photo/closeup-classic-fresh-espresso-served-dark-surface_1220-5376.jpg"
        ),
// ===================== AMERICANO =====================
        Product(
            id = 31,
            name = "Classic Americano",
            subtitle = "Espresso with hot water",
            description =
            "Americano made with fresh espresso and hot water. " +
                    "Smooth and mild coffee flavor with gentle body. " +
                    "Less intense than espresso, perfect for casual coffee lovers. " +
                    "Great choice for mornings or long work sessions. " +
                    "Serve hot for best aroma and taste.",
            price = 320.0,
            rating = 4.5,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://img.freepik.com/premium-photo/cup-coffee-wooden-background_392895-95927.jpg"
        ),

        Product(
            id = 32,
            name = "Vanilla Americano",
            subtitle = "Americano with vanilla syrup",
            description =
            "Americano prepared with espresso, hot water, and vanilla syrup. " +
                    "Smooth flavor with subtle sweetness and aroma. " +
                    "Light and refreshing, perfect for any time of day. " +
                    "Ideal for coffee lovers who enjoy flavored drinks. " +
                    "Serve warm for the best taste experience.",
            price = 330.0,
            rating = 4.6,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJt5xkl1GMo4W83hLF317J2H0OTT4s_rJNSw&s"
        ),

        Product(
            id = 33,
            name = "Caramel Americano",
            subtitle = "Americano with caramel touch",
            description =
            "Americano made with espresso, hot water, and caramel syrup. " +
                    "Rich caramel aroma complements mild coffee taste. " +
                    "Smooth texture creates cozy drinking experience. " +
                    "Perfect for dessert-style coffee moments. " +
                    "Best served warm for full flavor.",
            price = 340.0,
            rating = 4.6,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://thumbs.dreamstime.com/b/caramel-macchiato-beautiful-art-top-hot-coffee-white-cup-wooden-background-121707878.jpg"
        ),

        Product(
            id = 34,
            name = "Iced Americano",
            subtitle = "Chilled espresso with water",
            description =
            "Americano poured over ice for refreshing taste. " +
                    "Smooth coffee flavor stays mild yet satisfying. " +
                    "Light and crisp texture perfect for hot days. " +
                    "Ideal for summer coffee breaks. " +
                    "Serve with optional sugar or milk.",
            price = 350.0,
            rating = 4.7,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://img.freepik.com/premium-photo/iced-black-coffee-cup-table-coffee-shop-cafe_1339-160664.jpg"
        ),

        Product(
            id = 35,
            name = "Espresso Americano",
            subtitle = "Americano with extra espresso shot",
            description =
            "Americano with double espresso for stronger flavor. " +
                    "Balanced coffee taste with smooth body. " +
                    "Perfect for mornings needing extra caffeine. " +
                    "Smooth yet bold drinking experience. " +
                    "Best served hot or warm.",
            price = 360.0,
            rating = 4.7,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2GminP2RBz0Zdry6ub1dwhUzfxiW2AR6CkA&s"
        ),

        Product(
            id = 36,
            name = "Cinnamon Americano",
            subtitle = "Spiced Americano coffee",
            description =
            "Americano flavored with cinnamon spice for warmth. " +
                    "Smooth coffee taste with gentle spice notes. " +
                    "Perfect for cozy mornings or evenings. " +
                    "Light and aromatic drinking experience. " +
                    "Serve hot to enjoy full aroma.",
            price = 345.0,
            rating = 4.6,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXGO3RNvGc47-OlcJGu-Y_EQDzv1zx3tK70w&s"
        ),

        Product(
            id = 37,
            name = "Honey Americano",
            subtitle = "Sweetened with natural honey",
            description =
            "Americano prepared with espresso, hot water, and honey. " +
                    "Gentle sweetness balances smooth coffee flavor. " +
                    "Light and creamy texture without milk. " +
                    "Ideal for a comforting drink. " +
                    "Best served warm during afternoons.",
            price = 350.0,
            rating = 4.6,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://thumbs.dreamstime.com/b/espresso-honey-coffee-glass-cup-jar-309834160.jpg"
        ),

        Product(
            id = 38,
            name = "Almond Americano",
            subtitle = "Americano with almond milk",
            description =
            "Americano made using almond milk for light nutty taste. " +
                    "Smooth and mild flavor with pleasant aroma. " +
                    "Perfect alternative for dairy-free coffee lovers. " +
                    "Ideal for relaxed mornings or light breaks. " +
                    "Serve warm for best experience.",
            price = 355.0,
            rating = 4.5,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPJLE3eqsVeSXf4WAWATo171cQt_rE20XU9Q&s"
        ),

        Product(
            id = 39,
            name = "Vanilla Almond Americano",
            subtitle = "Flavored Americano",
            description =
            "Americano prepared with espresso, hot water, vanilla syrup, and almond milk. " +
                    "Smooth, aromatic flavor with creamy texture. " +
                    "Perfect for flavored coffee lovers. " +
                    "Ideal for a cozy afternoon drink. " +
                    "Serve warm for full enjoyment.",
            price = 365.0,
            rating = 4.7,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://media.istockphoto.com/id/2215411866/photo/coffee-cup-and-coffee-beans-on-wooden-table-espresso-crema-coffee-cup-aromatic.jpg?s=612x612&w=0&k=20&c=amd2V29_GYOZnRYdX7kOuN3h8UubtCEjvdYPSUaHggc="
        ),

        Product(
            id = 40,
            name = "Extra Smooth Americano",
            subtitle = "Mild and balanced",
            description =
            "Americano made with smooth espresso and hot water. " +
                    "Light and balanced flavor for any time of day. " +
                    "Gentle aroma enhances coffee experience. " +
                    "Perfect for relaxing coffee breaks. " +
                    "Serve hot for best enjoyment.",
            price = 350.0,
            rating = 4.6,
            category = CoffeeCategory.AMERICANO,
            imageUrl = "https://png.pngtree.com/thumb_back/fh260/background/20220608/pngtree-americano-coffee-in-wood-background-powder-spoon-cup-photo-image_31187994.jpg"
        ),


        )
}
