import os
import json
import comtypes.client
import tkinter as tk
from tkinter import filedialog, messagebox

CONFIG_FILE = "config.json"

def load_saved_config():
    if os.path.exists(CONFIG_FILE):
        with open(CONFIG_FILE, "r", encoding="utf-8") as f:
            try:
                config = json.load(f)
                output_var.set(config.get("last_output", ""))
                remember_var.set(config.get("remember", False))
            except json.JSONDecodeError:
                pass

def save_config():
    if remember_var.get():
        config = {
            "last_output": output_var.get(),
            "remember": True
        }
        with open(CONFIG_FILE, "w", encoding="utf-8") as f:
            json.dump(config, f)
    else:
        if os.path.exists(CONFIG_FILE):
            os.remove(CONFIG_FILE)

def convert_pptx_to_pdf(input_folder, output_folder):
    try:
        powerpoint = comtypes.client.CreateObject("PowerPoint.Application")
        powerpoint.Visible = 1

        for filename in os.listdir(input_folder):
            if filename.endswith(".pptx"):
                in_path = os.path.join(input_folder, filename)
                out_path = os.path.join(output_folder, filename.replace(".pptx", ".pdf"))
                try:
                    presentation = powerpoint.Presentations.Open(in_path)
                    presentation.SaveAs(out_path, FileFormat=32)
                    presentation.Close()
                except Exception as e:
                    print(f"⚠️ {filename} 변환 실패: {e}")
        powerpoint.Quit()
        messagebox.showinfo("완료", "모든 파일이 PDF로 변환되었습니다.")
    except Exception as e:
        messagebox.showerror("오류", f"PowerPoint 실행 중 오류: {e}")

def select_input_folder():
    path = filedialog.askdirectory(title="PPTX 파일 폴더 선택")
    if path:
        input_var.set(path)

def select_output_folder():
    path = filedialog.askdirectory(title="PDF 저장 폴더 선택")
    if path:
        output_var.set(path)

def start_conversion():
    input_path = input_var.get()
    output_path = output_var.get()
    if not input_path or not output_path:
        messagebox.showwarning("입력 필요", "두 폴더 모두 선택해주세요.")
        return
    save_config()
    convert_pptx_to_pdf(input_path, output_path)

# UI
root = tk.Tk()
root.title("PPTX → PDF 변환기")
root.geometry("600x180")

input_var = tk.StringVar()
output_var = tk.StringVar()
remember_var = tk.BooleanVar()

tk.Label(root, text="📂 PPTX 폴더").grid(row=0, column=0, padx=10, pady=10, sticky="e")
tk.Entry(root, textvariable=input_var, width=50).grid(row=0, column=1)
tk.Button(root, text="찾기", command=select_input_folder).grid(row=0, column=2)

tk.Label(root, text="💾 PDF 저장 폴더").grid(row=1, column=0, padx=10, pady=10, sticky="e")
tk.Entry(root, textvariable=output_var, width=50).grid(row=1, column=1)
tk.Button(root, text="찾기", command=select_output_folder).grid(row=1, column=2)

tk.Checkbutton(root, text="📌 저장 폴더 기억하기", variable=remember_var).grid(row=2, column=1, sticky="w", padx=5)

tk.Button(root, text="📄 변환 시작", command=start_conversion, bg="lightblue", width=20).grid(row=3, column=1, pady=15)

load_saved_config()
root.mainloop()
