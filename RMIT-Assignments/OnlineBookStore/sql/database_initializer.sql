drop table order_line;
drop table orders;
drop table comment;
drop table book;
drop table category;
drop table users;

create table users(
    id int not null,
    login varchar(255) unique not null,
    email varchar(255) unique not null,
    password varchar(255) not null,
    phone int,
    fullname varchar(255),
    birthday date,
    date_created date not null default current_date,
    primary key(id)
);

create table category(
    id int not null,
    category_name varchar(255) not null unique,
    sort_order int not null default 1,
    primary key(id)
);

create table book(
    id int not null,
    title varchar(255) not null,
    description varchar(10000),
    author varchar(255) not null,
    publisher varchar(255) not null,
    date_published date not null,
    price double not null,
    photo varchar(255),
    rating_value int default 0,
    rating_count int default 0,
    category_id int not null,
    foreign key(category_id) references category(id),
    primary key(id)
);

create table comment(
    id int not null,
    rating int default 0,
    content varchar(10000),
    user_id int not null,
    book_id int not null,
    comment_date date not null default current_date,
    foreign key(user_id) references users(id),
    foreign key(book_id) references book(id),
    primary key(id)
);

create table orders(
    id int not null,
    order_date date not null default current_date,
    amount double not null,
    user_id int not null,
    address varchar(255) not null,
    foreign key(user_id) references users(id),
    primary key(id)
);

create table order_line(
    id int not null,
    order_id int not null,
    book_id int not null,
    unit_price double not null,
    quantity int not null default 1,
    foreign key(order_id) references orders(id),
    foreign key(book_id) references book(id),
    primary key(id)
);

insert into users(
    id,
    login,
    email,
    password,
    phone,
    fullname,
    birthday
) values (
    1,
    'user1',
    'user1@onlinebookstore.com.vn',
    'password',
    1234567890,
    'User 1',
    '1991-1-1'
),(
    2,
    'user2',
    'user2@onlinebookstore.com.vn',
    'password',
    1234567890,
    'User 2',
    '1991-2-2'
),(
    3,
    'user3',
    'user3@onlinebookstore.com.vn',
    'password',
    1234567890,
    'User 3',
    '1991-3-3'
);

insert into category values(
    1,
    'Computers & Internet',
    1
), (
    2,
    'Entertainment',
    3
), (
    3,
    'Sports',
    2
);

insert into book values(
    1,
    'Idea Man',
    '"The entire conversation took five minutes. When it was over, Bill and I looked at each other. It was one thing to talk about writing a language for a microprocessor and another to get the job done . . . If we''d been older or known better, Bill and I might have been put off by the task in front of us. But we were young and green enough to believe that we just might pull it off." <br/><br/> Paul Allen, best known as the cofounder of Microsoft, has left his mark on numerous fields, from aviation and science to rock ''n'' roll, professional sports, and philanthropy. His passions and curiosity have transformed the way we live.  In 2007 and again in 2008, <i>Time</i> named him one of the hundred most influential people in the world. <br/><br/> It all started on a snowy day in December 1974, when he was twenty-one years old. After buying the new issue of <i>Popular Electronics</i> in Harvard Square, Allen ran to show it to his best friend from Seattle, Bill Gates, then a Harvard undergrad. The magazine''s cover story featured the Altair 8800, the first true personal computer; Allen knew that he and Gates had the skills to code a programming language for it. When Gates agreed to collaborate on BASIC for the Altair, one of the most influential partnerships of the digital era was up and running. <br/><br/> While much has been written about Microsoft''s early years, Allen has never before told the story from his point of view.  Nor has he previously talked about the details of his complex relationship with Gates or his behind-closed- doors perspective on how a struggling startup became the most powerful technology company in the world. <i>Idea Man</i> is the candid and long-awaited memoir of an intensely private person, a tale of triumphant highs and terrifying lows. <br/><br/> After becoming seriously ill with Hodgkin''s lymphoma in 1982, Allen began scaling back his involvement with Microsoft. He recovered and started using his fortune-and his ideas-for a life of adventure and discovery, from the first privately funded spacecraft (SpaceShipOne) to a landmark breakthrough in neuroscience (the Allen Brain Atlas). His eclectic ventures all start with the same simple question: What <i>should</i> exist?  As Allen has written: <br/><br/> <i>To me, that''s the most exciting question imaginable. . . . From technology to science to music to art, I''m inspired by those who''ve blurred the boundaries, who''ve looked at the possibilities, and said, "What if...?"  In my own work, I''ve tried to anticipate what''s coming over the horizon, to hasten its arrival, and to apply it to people''s lives in a meaningful way. . . . The varied possibilities of the universe have dazzled me since I was a child, and they continue to drive my work, my investments, and my philanthropy. </i><br/><br/> <i>Idea Man</i> is an astonishing true story of ideas made real.',
    'Paul Allen',
    'Portfolio Hardcover',
    '2011-4-19',
    16.48,
    'book_images/51-yqFdVngL._SS500_.jpg',
    10,
    3,
    1
), (
    2,
    'The Velocity Manifesto',
    'In this hands-on guide to helping leaders effectively steer their organizations through wrenching infrastructure and social changes, author Scott Klososky details the actions that leaders must take to keep their digital plumbing - the all-important technological infrastructure of their organizations - up-to-date. <p class="more_details"> Readers will learn that the survival of their organization depends on continuously adapting and integrating appropriate new technologies, including everything from robust IT systems to social technologies. Looking into the future, the author presents readers with a planning strategy that will enable them to stay ahead of burgeoning technological trends - especially those that will significantly impact their organizations. </p><p class="more_details"> Klososky deftly combines his discussion of technology with an in-depth, actionable program for workforce integration. By providing essential educational tools, he presents a process for building technology bridges across generations to maximize performance, loyalty, and satisfaction. This call-to-action will energize readers who are frustrated by cloistered IT departments, unproductive lines of communication between Baby Boomers and younger staff members, and an overall lack of technological sophistication. </p><p class="more_details"> By following this book readers will be able to revitalize any business culture threatened by technology. </p><p class="more_details"> With experience at every level of the technology business, from assembling computers as a teenager to delivering TED talks in Mumbai, Scott Klososky is a recognized expert in technological innovation.',
    'Scott Klososky',
    'Greenleaf Book Group Press',
    '2011-2-1',
    16.47,
    'book_images/Qffs+v35leryeeQWb1CWFycCUqS1VZ935HdTFtWzSPjJqmHsGB4ObUSVpECGqw7aoEoWnFJFRYM=.jpg',
    8,
    3,
    1
), (
    3,
    'Born To Run',
    'Full of incredible characters, amazing athletic achievements, cutting-edge science, and, most of all, pure inspiration, <i>Born to Run</i> is an epic adventure that began with one simple question: <i>Why does my foot hurt?</i> In search of an answer, Christopher McDougall sets off to find a tribe of the world’s greatest distance runners and learn their secrets, and in the process shows us that everything we thought we knew about running is wrong.<br/>    <p class="more_details">Isolated by the most savage terrain in North America, the reclusive Tarahumara Indians of Mexico’s deadly Copper Canyons are custodians of a lost art. For centuries they have practiced techniques that allow them to run hundreds of miles without rest and chase down anything from a deer to an Olympic marathoner while enjoying every mile of it. Their superhuman talent is matched by uncanny health and serenity, leaving the Tarahumara immune to the diseases and strife that plague modern existence. With the help of Caballo Blanco, a mysterious loner who lives among the tribe, the author was able not only to uncover the secrets of the Tarahumara but also to find his own inner ultra-athlete, as he trained for the challenge of a lifetime: a fifty-mile race through the heart of Tarahumara country pitting the tribe against an odd band of Americans, including a star ultramarathoner, a beautiful young surfer, and a barefoot wonder.</p>    <p class="more_details">With a sharp wit and wild exuberance, McDougall takes us from the high-tech science labs at Harvard to the sun-baked valleys and freezing peaks across North America, where ever-growing numbers of ultrarunners are pushing their bodies to the limit, and, finally, to the climactic race in the Copper Canyons. <i>Born to Run</i> is that rare book that will not only engage your mind but inspire your body when you realize that the secret to happiness is right at your feet, and that you, indeed all of us, were born to run.</p>',
    'Christopher McDougall',
    'Vintage',
    '2011-3-29',
    8.96,
    'book_images/8=.jpg',
    13,
    3,
    3
), (
    4,
    'Bottom Of The 33rd',
    '<i>No description available</i>',
    'Dan Barry',
    'Harper',
    '2011-4-12',
    17.09,
    'book_images/51hG4TRgq7L._SS500_.jpg',
    9,
    3,
    3
), (
    5,
    'Skipped Parts',
    '<i>No description available</i>',
    'Tim Sandlin',
    'Sourcebooks Landmark',
    '2010-9-1',
    10.19,
    'book_images/F0Vv+gqh0u5OC3hL5Ke1B1PI8eaRUTqRfzm218=.jpg',
    11,
    3,
    2
), (
    6,
    'Life',
    '<i>No description available</i>',
    'Keith Richards',
    'Back Bay Books',
    '2011-5-3',
    11.31,
    'book_images/Qffs+v35leqI5aJNtPQvAme0VMsRG+xfAqH8s+3bVf5LnAYG3k7QKp5d6BCDpudYM1YaKEDjB8A=.jpg',
    11,
    3,
    2
);

insert into orders values(
    1,
    current_date,
    105.02,
    1,
    '24 Su Van Hanh Street, District 10, Ho Chi Minh City, Vietnam'
), (
    2,
    current_date,
    163.6,
    2,
    '100 Nguyen Dinh Chieu St., Dist. 1, HCMC, Vietnam'
), (
    3,
    current_date,
    21.5,
    3,
    '48 Vanh Dai Trong St., Binh Tan Dist., HCMC, Vietnam'
);

insert into order_line values(
    1,
    1,
    1,
    16.48,
    5
), (
    2,
    1,
    6,
    11.31,
    2
), (
    3,
    2,
    3,
    8.96,
    3
), (
    4,
    2,
    4,
    17.09,
    8
), (
    5,
    3,
    6,
    11.31,
    1
), (
    6,
    3,
    5,
    10.19,
    1
);

insert into comment values(
    1,
    5,
    'Excellent!',
    1,
    1,
    current_date
), (
    2,
    2,
    'Poor content.',
    1,
    2,
    current_date
), (
    3,
    4,
    'Quite good!',
    1,
    3,
    current_date
), (
    4,
    1,
    'I totally hate it!',
    1,
    4,
    current_date
), (
    5,
    3,
    'Not bad!',
    1,
    5,
    current_date
), (
    6,
    5,
    'Pretty good!',
    1,
    6,
    current_date
), (
    7,
    1,
    'Very bad!',
    2,
    1,
    current_date
), (
    8,
    2,
    'Not quite interesting!',
    2,
    2,
    current_date
), (
    9,
    5,
    'Kind of excellent.',
    2,
    3,
    current_date
), (
    10,
    4,
    'It''s great!',
    2,
    4,
    current_date
), (
    11,
    4,
    'Exciting!',
    2,
    5,
    current_date
), (
    12,
    1,
    'Not interesting at all!',
    2,
    6,
    current_date
), (
    13,
    4,
    'Cool',
    3,
    1,
    current_date
), (
    14,
    4,
    'Cool',
    3,
    2,
    current_date
), (
    15,
    4,
    'Cool',
    3,
    3,
    current_date
), (
    16,
    4,
    'Cool',
    3,
    4,
    current_date
), (
    17,
    4,
    'Interesting',
    3,
    5,
    current_date
), (
    18,
    5,
    'Absolutely exciting!',
    3,
    6,
    current_date
);