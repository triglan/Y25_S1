import tkinter as tk
from tkinter import messagebox
import webbrowser

def open_urls():
    base_url = entry_url.get().strip()
    target = entry_target.get().strip()
    try:
        start = int(entry_start.get())
        end = int(entry_end.get())
    except ValueError:
        messagebox.showerror("입력 오류", "시작값과 끝값은 숫자여야 합니다.")
        return

    if target not in base_url:
        messagebox.showerror("입력 오류", f"'{target}' 이 주소에 없습니다.")
        return

    for i in range(start, end + 1):
        new_url = base_url.replace(target, str(i), 1)
        print("열기:", new_url)
        webbrowser.open_new_tab(new_url)

# GUI 구성
root = tk.Tk()
root.title("주소 자동 열기기")

tk.Label(root, text="기본 주소:").grid(row=0, column=0, sticky="e")
entry_url = tk.Entry(root, width=60)
entry_url.grid(row=0, column=1)

tk.Label(root, text="바꿀 부분 (예: 68426194):").grid(row=1, column=0, sticky="e")
entry_target = tk.Entry(root, width=20)
entry_target.grid(row=1, column=1, sticky="w")

tk.Label(root, text="시작 번호:").grid(row=2, column=0, sticky="e")
entry_start = tk.Entry(root, width=10)
entry_start.grid(row=2, column=1, sticky="w")

tk.Label(root, text="끝 번호:").grid(row=3, column=0, sticky="e")
entry_end = tk.Entry(root, width=10)
entry_end.grid(row=3, column=1, sticky="w")

tk.Button(root, text="열기", command=open_urls).grid(row=4, column=0, columnspan=2, pady=10)

root.mainloop()
