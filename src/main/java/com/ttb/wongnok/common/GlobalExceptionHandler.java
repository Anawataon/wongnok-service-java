package com.ttb.wongnok.common;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ttb.wongnok.model.dto.BaseResponse;

import jakarta.persistence.EntityNotFoundException;

// ทำให้ class นี้เป็นตัวจัดการ Exception แบบ global (ทุก controller จะมาเข้า class นี้)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ระบุว่า method นี้จะ handle exception ประเภทไหนจับเฉพาะ exception นี้
    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // method นี้ใช้สำหรับจัดการ validation error (เช่น @NotNull, @Size, @Email
    // fail)
    // รับ parameter ex คือ object ของ exception
    public ResponseEntity<BaseResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {
        // ดึง error ทั้งหมดจาก BindingResult ที่อยู่ใน exception
        String errorMsg = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", ")); // รวมทั้งหมดเป็นข้อความเดียว

        // ส่งกลับเป็น api response 400 Bad Request พร้อมข้อความ error
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(null, errorMsg));
    }

    @ExceptionHandler(EntityNotFoundException.class) // จับเฉพาะ EntityNotFoundException
    // method นี้จัดการกรณีหา entity ไม่เจอใน JPA เช่น findById(id).orElseThrow(...)
    public ResponseEntity<BaseResponse<String>> handleNotFound(EntityNotFoundException ex) {
        // ส่งกลับเป็น api response 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>(null, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class) // จับทุก exception ที่ไม่ถูกจับข้างบน
    public ResponseEntity<BaseResponse<String>> handleGeneral(Exception ex) {
        // ส่งกลับเป็น api response 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse<>(null, "เกิดข้อผิดพลาดภายในระบบ"));
    }

    // จัดการกรณี parameter ที่ไม่ตรงกับ type ที่คาดหวัง เช่น path variable เป็น String แต่คาดว่าเป็น Long
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponse<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMsg = "Invalid value for parameter '" + ex.getName() + "': " + ex.getValue();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(null, errorMsg));
    }
}