INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_ADMIN');

INSERT INTO `user_extra` (`id`, `iin`, `name`, `surname`) VALUES
(1, NULL, NULL, NULL);

INSERT INTO `memory` (`id`, `name`, `user_id`) VALUES
(1, 'qwe memory', 1);

INSERT INTO `users` (`id`, `email`, `name`, `password`, `profession`, `resume`, `surname`, `username`, `filedb_id`, `memory_id`, `user_extra_id`) VALUES
(1, 'qwe@gmail.com', NULL, '$2a$10$H8DLQmdtoDtpcoBHLAncs.PWn9Lq9ftYwi4j9vGobW6C0kbjiLgnm', NULL, NULL, NULL, 'qwe', NULL, 1, 1);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 3);
