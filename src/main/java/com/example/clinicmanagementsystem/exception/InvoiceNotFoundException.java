package com.example.clinicmanagementsystem.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException() {
        super("Invoice Not Found!!");
    }
}
