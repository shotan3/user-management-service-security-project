INSERT INTO "user"."user"."user"(user_uuid, first_name, last_name, date_of_birth, gender, primary_email,
                                 contact_phone, country_of_residence, city_of_residence, role_name)
VALUES ('f15a8f60-3201-4660-9864-d4527f09d03b', 'Shota', 'Andghuladze', '2002-03-20', 'MALE', 'test@test.com',
        '+995599000000', 'Georgia', 'Tbilisi', 'ADMIN');

INSERT INTO "user"."user".user_secret(curr_password, prev_password, password_last_update_time, user_uuid)
VALUES ('$2a$12$IKI/bpq/rMVEhTKfNJPi6e/RNOavoOx.7p/d0ZUtZjk.J8EPoOTl6',
        '$2a$12$IKI/bpq/rMVEhTKfNJPi6e/RNOavoOx.7p/d0ZUtZjk.J8EPoOTl6', '2024-03-30',
        'f15a8f60-3201-4660-9864-d4527f09d03b');

INSERT INTO "user"."user".user_account_info(created_at, updated_at, marked_for_deletion, deletion_scheduled_at,
                                            user_uuid)
VALUES ('2024-03-30', '2024-03-30', false, '2026-03-30', 'f15a8f60-3201-4660-9864-d4527f09d03b');