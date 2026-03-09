import os
import re

SOURCE_FOLDER = "src"          # change to your java project folder
OUTPUT_FILE = "ALL_CODE_REVIEW.java"


def clean_java_code(code):
    # remove package declarations
    code = re.sub(r'package\s+.*?;', '', code)

    return code


with open(OUTPUT_FILE, "w") as output:

    for root, dirs, files in os.walk(SOURCE_FOLDER):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)

                with open(file_path, "r") as f:
                    code = f.read()

                code = clean_java_code(code)

                output.write("\n")
                output.write("// ==============================\n")
                output.write(f"// FILE: {file_path}\n")
                output.write("// ==============================\n")
                output.write(code)
                output.write("\n\n")

print("Done. All Java code merged into:", OUTPUT_FILE)