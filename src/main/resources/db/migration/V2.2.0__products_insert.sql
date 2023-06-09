-- вставка данных в таблицу products
INSERT INTO products (product_name, product_type_id, product_size, supplier_id, price, discount, document_id,
                      is_guarantee, customs_price, arrival_date, final_price, final_discount)
VALUES ('Диск тормозной передний FORD Focus 2011', 3, 50, 1, 5000.0, 10, 'doc123', true, 4500.0,
        null, 4000.0, 5),
       ('Фильтр масляный для BMW 320i 2018', 2, 20, 3, 1500.0, 5, 'doc456', false, 1400.0, null, 1200.0, 5),
       ('Колесо заднее для Toyota Camry 2015', 1, 70, 5, 4000.0, 15, 'doc789', true, 3500.0, null, 3200.0, 10),
       ('Панель двери для Chevrolet Aveo 2008', 6, 300, 4, 10000.0, 0, 'doc012', false, 9000.0, null, 9000.0, 0),
       ('Двигатель для Lada Priora 2010', 5, 800, 2, 30000.0, 20, 'doc345', true, 25000.0, null, 20000.0, 20);
--        ('Колесо литое 18" BMW X5', 1, 70, 5, 120, 20, true, 90, 96, 15),
--        ('Колесо штампованное 15" Renault Logan', 1, 55, 4, 40, 5, true, 30, 38, 3),
--        ('Стекло переднее BMW X5', 4, 200, 5, 300, 0, false, 150, 300, 0),
--        ('Стекло заднее Renault Logan', 4, 180, 4, 200, 15, true, 110, 170, 10),
--        ('Аккумулятор Bosch 60Ач', 5, 100, 1, 80, 10, true, 60, 72, 7),
--        ('Масло Mobil 1 5W-40', 9, 10, 1, 35, 0, false, 25, 35, 0),
--        ('Свеча зажигания NGK BKR6E', 9, 2, 5, 8, 0, false, 3, 8, 0),
--        ('Колесо легкосплавное 17 R17 5x112 ET30', 1, 18, 2, 10000, 0, 'DOC-002', false, 4000, 6000, 0),
--        ('Масляный фильтр Mann Filter HU 719/7 x', 2, 10, 3, 300, 5, NULL, true, 200, 285, 2),
--        ('Лобовое стекло Audi Q5 2015', 4, 200, 5, 15000, 0, NULL, true, 7000, 8000, 0);