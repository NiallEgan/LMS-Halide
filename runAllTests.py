import subprocess
import os

def runTest(prog):
    subprocess.call(["gcc", "-mavx", "-mavx2", "comparision_test.c", "testOutput/" + prog])
    subprocess.call(["./a.out"])


if __name__ == '__main__':
    for filename in os.listdir("testOutput"):
        # only compare the two stage blurs (different overflow behaviour), fuse one stage
        if "blur" in filename and filename.endswith(".c") and not "grad" in filename and not "one" in filename and not "fused" in filename:
            print(filename)
            runTest(filename)
