Ali ŞİMŞEK 

# Anadolubank Java Bootcamp Bitirme Projesi

## Proje Açıklaması
2030 yılında, siz bir Sigorta Şirketinde Kıdemli Yazılım Geliştirici olarak görev almaya başladınız. Birkaç ay içerisinde Sigorta Şirketinin finansal işleyişini öğrendiniz. Üst düzey yöneticiler, finansal olarak Sigorta Şirketinin iyi durumda olduğunu biliyorlar ancak planlama sırasında yaşanan aksaklıklar nedeniyle ciddi karları kaybettiğini tespit ettiler. Hem internet üzerinden yapılan araştırmalar hem de çalışanlardan alınan geri dönüşler sayesinde üst düzey yöneticiler Sigorta Şirketi için bir İş Zekası sistemi geliştirilmesi gerektiğine karar verdiler. Üst düzey yöneticiler, bu yazılımın geliştirilmesi görevini size verdi.   

### Projenin Teknik Gereksinimleri
- Kullanıcı sisteme ait bileşenlere güvenli giriş yaparak erişmelidir. (Zorunlu değil)
- Kullanıcı geçmiş dönemlere ait finansal verilere ulaşabilecektir. Kullanıcı geçmiş döneme ait verilere tablo formatında erişebilmeli veya PL/SQL sorgulama dili kullanarak erişebilmelidir.
- Kullanıcı geçmiş döneme ilişkin verileri kullanarak tablo görünümünde rapor oluşturabilmelidir.
- Kullanıcı geçmiş döneme ilişkin verileri birbirleriyle karşılaştırabilmelidir.
- Kullanıcı geçmiş döneme ait verileri grafik üzerinde görebilmelidir.


## ER DIAGRAM  

![final_project_ER_diagram](https://user-images.githubusercontent.com/93866835/182036554-e5c30eea-fe92-463f-b54a-69286a01240c.png)



## USE CASE

![Use Case](https://user-images.githubusercontent.com/93866835/182036573-87d7aea5-4609-42c9-919c-ca9311bc925f.png)



- Kullanıcı email ve şifresi ile sisteme giriş yapar. Eğer sisteme kayıtlı değilse veya hatalı giriş yaptıysa uyarı ile karşılaşır.  
- Giriş yaptıktan sonra açılan ekranın sol tarafındaki menüden isteği doğrultusunda poliçelere ait filtreleme yaparak geçmiş dönem verileri tablo olarak görmek için rapor oluşturur.  
- Raporda yer alan verilerle aşağıdaki gibi 3 farklı grafik oluşturabilir.


![1](https://user-images.githubusercontent.com/93866835/182036355-ff4d897f-75c1-48ac-a585-5114b20bc8c8.png)
![2](https://user-images.githubusercontent.com/93866835/182036629-ba2e9cff-1fef-4a72-8700-131cc635c0ce.png)
![3](https://user-images.githubusercontent.com/93866835/182036631-2a227633-dfbb-41f5-87af-3c9da5d7243a.png)
![4](https://user-images.githubusercontent.com/93866835/182036633-4732c0ac-5ab2-40ec-876d-4653897da348.png)
![5](https://user-images.githubusercontent.com/93866835/182036635-910151b7-eab8-4773-a250-52ce509f73c7.png)
![6](https://user-images.githubusercontent.com/93866835/182036639-3c348c2d-84d4-4985-ba27-8a28cb57e7cc.png)
![7](https://user-images.githubusercontent.com/93866835/182036643-f8db7284-8658-4eaf-8afe-5ac8d4a2604b.png)
![8](https://user-images.githubusercontent.com/93866835/182036645-b5513a12-58b0-4f73-9534-1e8d7f77b95a.png)