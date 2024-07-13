INSERT INTO chat.users (login, password) VALUES
                                             ('user1', 'password1'),
                                             ('user2', 'password2'),
                                             ('user3', 'password3'),
                                             ('user4', 'password4'),
                                             ('user5', 'password5');

INSERT INTO chat.chatrooms (name, ownerid) VALUES
                                            ('Room1', 1),
                                            ('Room2', 2),
                                            ('Room3', 3),
                                            ('Room4', 4),
                                            ('Room5', 5);

INSERT INTO chat.messages (authorID, chatroomID, text, datetime) VALUES
                                                             (1, 1, 'Hello from user1 in Room1', '2023-11-10 12:00:00'),
                                                             (2, 1, 'Hi from user2 in Room1', '2023-11-10 12:05:00'),
                                                             (3, 2, 'Greetings from user3 in Room2', '2023-11-10 12:10:00'),
                                                             (4, 2, 'Message from user4 in Room2', '2023-11-10 12:15:00'),
                                                             (5, 3, 'Welcome from user5 in Room3', '2023-11-10 12:20:00');

INSERT INTO chat.user_chatrooms (userID, chatroomID) VALUES
                                                           (1, 1),
                                                           (2, 1),
                                                           (2, 2),
                                                           (3, 2),
                                                           (3, 3);