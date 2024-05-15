package com.automatizacija.email;

public interface EmailSender {

	void send(String to, String email);

    String emailKreiranje(String name, String link);
}
