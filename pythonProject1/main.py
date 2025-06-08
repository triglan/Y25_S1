
import requests
from bs4 import BeautifulSoup
import os
import time

def download_images(urls, cookie):
    folder_name = "hellven_images"
    os.makedirs(folder_name, exist_ok=True)
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        "Referer": "https://hellven.net/",
        "Cookie": "56glc39rns2q59oe2ji3qs8vh7n2cfvj"
    }

    for index, url in enumerate(urls):
        try:
            print(f"🔗 요청 중: {url}")
            res = requests.get(url, headers=headers, timeout=10)
            soup = BeautifulSoup(res.text, "html.parser")
            imgs = soup.find_all("img")

            for i, img in enumerate(imgs):
                src = img.get("src")
                if not src:
                    continue
                if src.startswith("//"):
                    src = "https:" + src
                elif src.startswith("/"):
                    src = "https://hellven.net" + src

                ext = os.path.splitext(src)[-1].split("?")[0]
                if not ext:
                    ext = ".jpg"
                filename = f"{folder_name}/img_{index}_{i}{ext}"

                try:
                    img_data = requests.get(src, headers=headers, timeout=10).content
                    with open(filename, "wb") as f:
                        f.write(img_data)
                    print(f"✅ 저장됨: {filename}")
                except Exception as e:
                    print(f"⚠️ 이미지 저장 실패: {src} - {e}")
                    continue

            time.sleep(1.5)  # 서버 차단 방지를 위한 딜레이

        except requests.exceptions.RequestException as e:
            print(f"❌ 요청 실패: {url}")
            print("사유:", e)
            continue

if __name__ == "__main__":
    print("💬 hellven.net 게시글 URL을 한 줄씩 입력하세요. (입력 종료: 빈 줄 Enter)")
    urls = []
    while True:
        line = input()
        if not line.strip():
            break
        urls.append(line.strip())

    if not urls:
        print("📭 입력된 URL이 없습니다.")
        exit()

    print("\n🔐 로그인된 브라우저에서 복사한 Cookie 값을 붙여넣으세요:")
    cookie = input("Cookie: ").strip()

    if not cookie:
        print("❌ 쿠키가 입력되지 않았습니다.")
        exit()

    download_images(urls, cookie)
    print("\n🎉 모든 작업 완료! 'hellven_images' 폴더를 확인하세요.")
