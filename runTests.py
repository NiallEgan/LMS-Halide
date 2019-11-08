import subprocess
import os
import argparse

def runTest(prog, img):
    subprocess.call(["gcc", "-w", "-lpng", "-mavx", "-mavx2", "testOutput/" + prog, "builder.c"])
    subprocess.call(["./a.out", "testImages/" + img,  "testImages/" + os.path.splitext(prog)[0] + '_' + img])

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Run some tests.')
    parser.add_argument('-f', '--file', default="",
                        help="(partial) name of tests to run")
    parser.add_argument('-i', '--image', default="bird.png",
                        help="image used for testing")
    args = parser.parse_args()

    for filename in os.listdir("testOutput"):
        if args.file in filename and filename.endswith(".c") and not "grad" in filename and not "one" in filename and not "fused" in filename:
            print(filename)
            runTest(filename, args.image)