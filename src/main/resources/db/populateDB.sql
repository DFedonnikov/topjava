DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('22/11/2017 10:22', 'Admin meal_1', 500, 100000),
  ('23/11/2017 18:26', 'Admin meal_2', 500, 100000),
  ('23/11/2017 20:26', 'Admin meal_3', 500, 100000),
  ('22/11/2017 10:45', 'User meal_1', 500, 100001),
  ('23/11/2017 18:35', 'User meal_2', 500, 100001),
  ('23/11/2017 20:45', 'User meal_3', 500, 100001)
