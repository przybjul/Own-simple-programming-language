; ModuleID = 'block.c'
target datalayout = "e-p:64:64:64-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-v64:64:64-v128:128:128-a0:0:64-s0:64:64-f80:128:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

define i32 @main() nounwind uwtable {
  %1 = alloca i32, align 4
  %x = alloca i32, align 4
  store i32 0, i32* %1
  store i32 5, i32* %x, align 4
  %2 = load i32* %x, align 4
  %3 = icmp eq i32 %2, 5
  br i1 %3, label %4, label %7

; <label>:4                                       ; preds = %0
  %5 = load i32* %x, align 4
  %6 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([4 x i8]* @.str, i32 0, i32 0), i32 %5)
  br label %7

; <label>:7                                       ; preds = %4, %0
  %8 = load i32* %1
  ret i32 %8
}

declare i32 @printf(i8*, ...)
