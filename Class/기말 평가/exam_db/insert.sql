-- 더미 데이터


-- Award
INSERT INTO `award`(`award_name`, `award_year`)
VALUES('Best Performance by an Actor in a Leading Role', 2016),
('Best Performance by an Actor in a Leading Role ', 2020),
('Best Motion Picture of the Year ', 2014),
('Best Performance by an Actor in a Leading Role', 2014),
('Best Performance by an Actor in a Leading Role', 2007),
('Best Performance by an Actor in a Leading Role ', 2005),
('Best Actor in a Supporting Role ', 1994),
('Best Writing, Original Screenplay', 2013),
('Best Writing, Screenplay Written Directly for the Screen', 1995),
('Best Achievement in Directing', 2020),
('Best Original Screenplay', 2020),
('Best Motion Picture of the Year', 2020),
('Best Director', 1995);



-- People
INSERT INTO `people` (`people_name`, `people_real_name`, `people_kor_name`, `people_birth`, `people_nationality`, `people_story`)
VALUES ('Quentin Tarantino', 'Quentin Jerome Tarantino', '쿠엔틴 타란티노', '1963-3-27', '녹스빌, 테네시, 미국', '쿠엔틴 제롬 타란티노는 간호사인 어머니와 뉴욕출신인 이탈리아계 미국인 아버지 사이에서 태어났다. 타란티노는 네 살때 어머니와 함께 캘리포니아 토렌스로 이사했다.
 1992년 1월, 선댄스 영화제에서 그의 첫 번째 각본-감독한 영화인 <저수지의 개들>(1992)이 소개되었다. 영화는 컬트적인 인기를 얻으며 단숨에 그를 전설로 만들었으며, 2년 후 칸 영화제에 <펄프 픽션>(1994)이 상영되었고, 이 영화는 아카데미 영화제에서 작품상, 감독상 및 각본상 후보에 올랐으며, 공동 각본 로저 에버리와 함께 각본상을 수생했다. 1995년...'),
('Leonardo DiCaprio', 'Leonardo Wilhelm DiCaprio', '레오나르도 디카프리오', '1974-11-11', '할리우드, 로스앤젤레스, 캘리포니아, 미국', 'Few actors in the world have had a career quite as diverse as Leonardo DiCaprio‘s. DiCaprio has gone from relatively humble beginnings, as a supporting cast member of the sitcom Growing Pains (1985) and low budget horror movies, such as Critters 3 (1991), to a major teenage heartthrob in the 1990s, ...'),
('Brad Pitt', 'William Bradley Pitt', '브래드 피트', '1963-12-18', '쇼니, 오클라호마, 미국 ', '배우이자 제작자인 브래드 피트는 잘 생긴 얼굴 만큼이나 다재다능한 것으로 알려져 있다. <12 몽키즈>(1995)에서 아카데미 남우 주연상에 노미네이션 되면서 연기력을 인정 받았고, <파이트 클럽>(1999)에서 멋진 연기를 보여주면서 배우로 주목 받았다. 그후 <벤자민 버튼의 시간은 거꾸로 간다>(2008)와 <머니볼>(2011)등의 영화에서 주목 받았으며, <월드워 Z>(2013), <빅 쇼트>(2015), <노예 12년>(2013)등의 영화를 제작하고 
 <데드풀 2>(2018)등의 영화에 까메오 출연했다. 그 후 <원스 어폰...'),
('Margot Robbie', 'Margot Elise Robbie', '마고 로비', '1990-7-2', '달비, 퀸스랜드, 오스트레일리아 ', 'Margot Robbie is an Australian actress born in Dalby, Queensland, and raised on the Gold Coast, spending much of her time at the farm belonging to her grandparents. Her mother, Sarie Kessler, is a physiotherapist. Robbie attended and graduated from Somerset College. In her late teens, she moved to M...');
 

 
 -- Role
 INSERT INTO `role` (`role_name`)
 VALUES ('배우'), ('제작자'), ('각본가'), ('감독');
 
 
 
 -- genre
 INSERT INTO `genre` (`genre_name`)
 VALUES ('드라마'), ('코미디'), ('액션'), ('어드벤처'), ('공상과학'), ('전기'), ('범죄');
 
 
 
 -- view_age
 INSERT INTO `view_age` (`view_age_name`)
 VALUES ('12세'), ('15세'), ('18세');
 
 
 
 -- Movie
 -- 1
 INSERT INTO `movie` (`movie_name`, `movie_kor_name`, `view_age_id`, `release_date`, `runtime`, `movie_story`, `movie_budget`, `usa_box_office`, `ww_box_office`, `movie_trivia`)
 VALUES ('Once Upon a Time... in Hollywood', '원스 어폰 어 타임... 인 할리우드', 1, '2019-9-25', 161,
 '쇠락해 가는 TV 스타 릭 달톤과 그의 친구인 스턴트 클리프 부스는 1969년 할리우드 황금기의 마지막 몇 년동안 영화 산업에서 명성을 쌓고 성공하기 위해 노력한다.',
 '$90,000,000', '$140,919,587', '$371,544,347',
 '브래드 피트는 "너는 릭 달톤이야! 잊어버리지 마!"라고 말하는데, 90년대 초 자신에게 이 말을 한 배우를 생각하며 대사를 만들었다.
샤론 테이트를 연기한 마고 로비가 찬 보석은 실제 샤론 테이트의 보석이다. 샤론 테이트의 동생 데브라가 로비에게 보석을 제공했다.
릭 달톤의 캐딜락은 마이클 매드슨의 차이며, 이 차는 <저수지의 개들>에도 출연했다.
릭 달톤이 대사를 잃어버린 것을 자책하는 트레일러 장면에서, 타란티노의 영화에서는 매우 드물게 즉흥 연기가 포함되었다.
중국은 이 영화가 이소룡을 묘사한 방식때문에 개봉을 금지했으며, 타란티노는 중국에서 영화를 개봉하기 위해 어떤 장면도 편집하지 않았다.
팀 로스가 출연했으나, 그의 장면은 통째로 편집되었다.
데미안 루이스가 연기한 스티브 맥퀸은 실제로 사건이 있던 날 샤론 테이트의 집으로 방문하기로 되어 있었지만 가지 않았다.
레오나르도 디카프리오와 브래드 피트는 제작 과정에서 매우 잘 어울렸고, 다른 영화에서도 같이 출연하고 싶다고 말했다.
칸 영화제에서 영화가 세계 최초로 상영되기 전에 전에, 쿠엔틴 타란티노 (Quentin Tarantino) 는 칸 (Cannes) 관중들에게 청중의 스포일러를 피하기 위해 간청했다. "영화를 좋아합니다. 영화를 좋아합니다. 처음으로 이야기를 발견하는 여행입니다. 칸 영화제에서 \'Once Upon A Time ... Hollywood\'를 축제 관객들과 함께 나누게되어 기쁩니다. 제작진은 독창적 인 작품을 만들기 위해 열심히 노력해 왔으며 모든 사람들이 나중에 청중이 같은 방식으로 영화를 경험하지 못하게하는 어떠한 것도 공개하지 말 것을 부탁합니다. 감사합니다. "
쿠엔틴 타란티노는 레오나르도 디카프리오와 브래드 피트가 로버트 레드포드와 폴 뉴먼 이후 가장 흥미로운 스타 듀오라고 이야기 했다.
플레이 보이 맨션의 파티 장면은 실제 플레이 보이 맨션에서 촬영되었으며, 쿠엔틴 타란티노는 휴 헤프너에게 여러차레 플레이 보이 맨션에 초대되었다.
목장 주인역으로 버트 레이놀즈가 캐스팅 되었지만, 촬영 전에 사망했다.
레오나르도 디카프리오는 쿠엔틴 타란티노와 다시 작업하기 위해 평소 출연료의 25%를 자진 삭감했다.
쿠엔틴 타란티노는 이 영화는 여러개의 이야기가 병렬적으로 구성되어 있으며, 그의 영화 중 <펄프 픽션>(1994)과 가장 비슷하다고 말했다.
프레드 라스킨의 첫 번째 편집 버전은 4시간 20분이었다.
샤론 테이트가 영화를 보는 장면에서, 그녀의 발이 더럽다는 것을 알 수 있는데 실제로 샤론 테이트는 신발을 신는 것을 싫어해 꼭 필요하지 않은 장소에서는 신발을 신지 않았다.
칸 영화제 시사회에서 7분간 기립 박수를 받았다.');

-- 2
INSERT INTO `movie` (`movie_name`, `movie_kor_name`, `view_age_id`, `release_date`, `runtime`, `movie_story`, `movie_trivia`)
VALUES ('Don’t Look Up', '돈 룩 업', 2, '2021-8-12', 138,
'천문학과 대학원생 케이트 디비아스키와 담당 교수 랜들 민디 박사는 태양계 내의 궤도를 돌고 있는 혜성이 지구와 직접 충돌하는 궤도에 들어섰다는 엄청난 사실을 발견한다.',
'제니퍼 로렌스는 촬영 중 치아가 부러졌으나 코로나 19로 인해 치과에 갈 수 없었고, 대부분의 장면을 치아가 부러진 채로 촬영했다. 치아는 후반 작업에서 CG로 수정했다.
헤어, 분장, 의상 부서는 디카프리오의 캐릭터가 최대한 단조롭고 섹스리스 인것처럼 보이게 하는데 최선을 다했다.
영화 속 대통령이 공화당이냐 민주당이냐는 질문에, 아담 멕케이 감독은 지난 40년간 어떤 정당도 자랑스러울 것이 없다고 생각한다고 대답했다.
이 영화에는 5명의 아카데미 연기상 수상자와 2명의 아카데미 연기상 후보가 출연한다.
멜라니 린스키는 이 영화 이전에 <세상의 끝까지 21일>(2012)에 출연했다. 두 영화 모두 세상의 멸망을 앞두고 중년 남성과 젊은 여성의 우정을 중심으로 전개된다.
1:41경에 QR 코드가 화면에 나타난다. 스캔하면 "Ariana Grande & Kid Cudi - Just Look Up (Full Performance Video) - Don\'t Look Up - Netflix." Youtube로 연결된다.'
);

-- 3
INSERT INTO `movie` (`movie_name`, `movie_kor_name`, `runtime`, `movie_story`, `movie_budget`, `usa_box_office`, `ww_box_office`)
VALUES ('Richard Jewell', '리처드 주얼', '131',
 'American security guard Richard Jewell saves thousands of lives from an exploding bomb at the 1996 Olympics, but is vilified by journalists and the press who falsely reported that he was a terrorist.',
 '$45,000,000', '$22,195,105', '$30,695,105');
 
 -- 4
INSERT INTO `movie` (`movie_name`, `movie_kor_name`, `release_date`, `runtime`, `movie_story`, `movie_budget`, `usa_box_office`, `ww_box_office`)
VALUES ('Django Unchained','장고: 분노의 추적자', '2013-3-21', '165', '독일인 현상금 사냥꾼에게 구출되어 자유의 몸이 된 흑인 노예 장고는 잔인한 농장주로 부터 아내를 구하기 위해 떠난다',
'$100,000,000', '$162,805,434', '$425,368,238');

-- 5
INSERT INTO `movie` (`movie_name`, `movie_kor_name`, `release_date`, `runtime`, `movie_story`, `movie_budget`, `usa_box_office`, `ww_box_office`)
VALUES ('Pulp Fiction', '펄프 픽션', '1994-10-9', '127',
'The lives of two mob hit men, a boxer, a gangster‘s wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
'$8,000,000', '$107,928,762', '$213,928,762');

-- 6
INSERT INTO `movie` (`movie_name`, `movie_kor_name`, `view_age_id`, `release_date`, `runtime`, `movie_story`, `movie_budget`, `usa_box_office`, `ww_box_office`, `movie_trivia`)
VALUES ('The Revenant', '레버넌트: 죽음에서 돌아온 자', 2, '2016-1-14', '156',
'1820년 모피 사냥 원정대에서 곰의 습격을 받아 심한 부상을 입은 남자가 자신의 팀원들에 의해 아들이 죽고 자신도 죽을 위기에 처한다. 죽음의 위기에서 살아난 남자가 복수를 시작한다.',
'$135,000,000', '$183,637,894', '$532,950,503',
'레오나르도 디카프리오는 촬영중 실제로 생 들소 간을 삼켰다. 그는 채식주의자이다.
톰 하디는 스프린터 셀에 출연을 고려하고 있던 중이라 <레버넌트>의 각본을 읽지 않았다. 레오나르도 디카프리오가 대본을 읽어보기를 간청했고, 톰 하디는 절반쯤 읽은 후 역할을 수락했다.
레오나르도 디카프리오는 <스티브 잡스>(2015)에 출연을 고려중이었지만 이 영화를 선택했다.
아서 레드클루드는 이 영화가 이전에 배우 경력이 없다. 그는 텍사스의 트럭 운ㅈ너사였으며, 석유를 운송하여 생계를 유지했다.
존 피츠제럴드 역에는 숀 펜이 결정되어 있었지만 스케출 중복으로 인해 톰 하디로 대체되었다.
레오나르도 디카프리오의 6번째 아카데미 후보작이자 첫 번째 수상작이다. 후에 <원스 어폰 어 타임.. 인 할리우드>(2019)으로 한번 더 후보에 올랐다.
톰 하디는 <플래툰>에서 톰 베린저의 연기에서 영감을 얻었다.
알레한드로 곤잘레스 이냐리투는 연속으로 아카데미 감독상을 수성한 3명의 감독중 한 사람이 되었다.
촬영감독 루베즈키는 이 영화로 3년 연속으로 오스카를 수상한 유일한 사람이 되었다.
레오나르도 디카프리오는 이 영화로 아카데미 남우주연상을 포함한 35개의 상을 수상했다.
영화에서 일어난 눈사태는 실제로 만든 것으로, 캐나다 앨버타의 포트레스 산에서 비행기로 폭탄을 떨어뜨려 눈사태를 일으켰다.
이 영화의 상영 시간은 2시간 36분으로, 알레한드로 곤잘레스 이냐리투 감독의 영화중 가장 길다.
레오나르도 디카프리오는 아카데미상, 골든 글로브 및 스크린 배우 조합 등 이 영화에서 그의 역할을 통해 지명된 거의 모든 상을 수상했습니다.
남우 주연상과 감독상을 수상했지만 작품상을 수상하지 못한 세 번쨰 영화이다.');
 
 
 
 -- movie_genre
INSERT INTO `movie_genre` (`movie_id`, `genre_id`)
VALUES (1, 1), (1, 2),
(2, 1), (2, 2), (2, 5),
(3, 1), (3, 6), (3, 7);
 
 
 
 -- award_nomination
 INSERT INTO `award_nomination` (`award_id`, `movie_id`, `people_id`)
 VALUES (1, 6, 2), (2, 1, 2), (8, 4, 1), (9, 5, 1), (10, 1, 1), (11, 1, 1), (12, 1, 1), (13, 5, 1);
 


-- award_winner
INSERT INTO `award_winner` (`award_nomination_id`)
VALUES (3), (4), (1);



-- movie_cast
INSERT INTO `movie_cast` (`movie_id`, `people_id`, `role_id`)
VALUES (1, 1, 4), (1, 1, 3), (1, 2, 1), (1, 3, 1), (1, 4, 1) ,(1, 1, 1);
 