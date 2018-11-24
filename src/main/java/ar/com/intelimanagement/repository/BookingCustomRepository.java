package ar.com.intelimanagement.repository;

import java.util.List;

import ar.com.intelimanagement.domain.Booking;

public interface BookingCustomRepository {

	List<Booking> find(String value);

}
