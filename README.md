# Bài tập lớn OOP - Bomberman Game

## **Mục lục**
1. Thành viên
2. Giới thiệu
3. Mô tả về các đối tượng trong trò chơi
4. Điều khiển và cài đặt
5. Chức năng bắt buộc và tuỳ chọn đã cài đặt
6. Chức năng bổ sung
7. Cây thừa kế
8. Demo

## **1. Thành viên** 
BTL nhóm 15, thành viên nhóm:
- Nguyễn Tuấn Đức, mã sinh viên 21020539
- Nguyễn Việt Anh, mã sinh viên 21020277

## **2. Giới thiệu**
Phiên bản làm lại bằng Java của tựa game Bomberman trên máy NES [Bomberman](https://www.youtube.com/watch?v=mKIOVwqgSXM)

## **3.Mô tả về các đối tượng trong trò chơi**
Nếu bạn đã từng chơi Bomberman, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (*Bomber*, *Enemy*, *Bomb*) và nhóm đối tượng tĩnh (*Grass*, *Wall*, *Brick*, *Portal*, *Item*).

*Hãy thiết kế hệ thống các đối tượng một cách phù hợp để tận dụng tối đa sức mạnh của OOP: tái sử dụng code, dễ dàng maintain.*

- ![](res/sprites/player_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi. 
- ![](res/sprites/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](res/sprites/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](res/sprites/explosion_horizontal.png) được tạo ra.


- ![](res/sprites/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](res/sprites/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](res/sprites/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.


- ![](res/sprites/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](res/sprites/powerup_speed.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp.
- ![](res/sprites/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn).
- ![](res/sprites/powerup_bombs.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.
- ![](res/sprites/powerup_flamepass.png) *FlamepassItem* sử dụng item này thì thì Bomber có thể đi xuyên qua và không bị ảnh hưởng bởi lửa từ Bom.
- ![](res/sprites/powerup_clock.png) *ClockItem* sử dụng item này thì thời gian mỗi màn chơi sẽ được tăng tối đa 30 giây giúp người chơi có thêm thời gian hoàn thành màn chơi và tăng thêm số điểm cửa mình.

Có nhiều loại Enemy trong Bomberman, cụ thể trong phiên bản này gồm:
- ![](res/sprites/balloom_left1.png) *Balloom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
- ![](res/sprites/oneal_left1.png) *Oneal* di chuyển ngẫu nhiên với vận tốc lớn hơn Balloom
- ![](res/sprites/doll_left1.png) *Doll* là Enemy di chuyển phức tạp hơn Oneal (biết đuổi theo Bomber)
- ![](res/sprites/minvo_left1.png) *Minvo* là Enemy di chuyển biết đuổi theo Bomber như Doll nhưng với tốc độ lớn hơn
- ![](res/sprites/kondoria_left1.png) *Kondoria* là Enemy di chuyển biết đuổi theo Bomber với vận tốc chậm nhất nhưng có thể đi xuyên qua soft block

## Mô tả game play, xử lý va chạm và xử lý bom nổ
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí Portal để có thể qua màn mới
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

- Khi Bomb nổ, một Flame trung tâm![](res/sprites/bomb_exploded.png) tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên![](res/sprites/explosion_vertical.png)/dưới![](res/sprites/explosion_vertical.png)/trái![](res/sprites/explosion_horizontal.png)/phải![](res/sprites/explosion_horizontal.png). Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.

## **4. Điều khiển và cài đặt**
| Control | Key   |
|---------|-------|
| UP      | ↑, w  |
| DOWN    | ↓, s  |
| LEFT    | ←,a   |
| RIGHT   | → ,d  |
| BOMB    |SPACE  |

|Menu            | Key |
|---------       |-----|
| Pause Game     | ESC |

## **5. Chức năng bắt buộc đã cài đặt**
# **Chức năng bắt buộc**
- Thiết kế cây thừa kế cho các đối tượng game.
- Xây dựng bản đồ màn chơi từ tệp cấu hình.
- Di chuyển Bomber theo sự điều khiển từ người chơi.
- Tự động di chuyển các enemies.
- Xử lý va chạm cho các đối tượng Bomber, Enemy, Wall, Brick, Bomb.
- Xử lí bom nổ.
- Xử lý khi Bomber sử dụng các Item và khi đi vào vị trí Portal.
# **Chức năng tuỳ chọn**
- Sử dụng Git trong nhóm (branch, commit, pull request, contribution, ...).
- "Nâng cấp thuật toán tìm đường cho Enemy.
- Cài đặt các loại Enemy khác.
- Xử lý hiệu ứng âm thanh.

## **6. Chức năng bổ sung**
- Có menu với các chức năng cần thiết.
- Có thể chọn level tuỳ thích khi đã mở khoá kể cả khi đóng game (sử dụng đọc ghi file).
- Tính điểm cho mỗi màn chơi.
- Lưu và hiển thị điểm cao (sử dụng đọc ghi file).
- Tuỳ chỉnh tắt bật BackgroundMusic và soundFx.
- Bộ đếm fps cho game.
- Có menu phụ và tuỳ chọn dừng trò chơi.
- Có bộ đếm giờ trong trò chơi.
- Có mạng trong mỗi lần chơi.
- Bổ sung thêm các Item và Enemy mới.

## **7 Cây thừa kế**
 - ![](res/readme/UML1.png)
 - ![](res/readme/UML2.png)

## **8 Demo video**
- https://www.youtube.com/watch?v=wba7TiTQodU
