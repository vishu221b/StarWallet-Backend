package com.StarWallet.StarWallet.service;

import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.repository.UserRepository;
import com.StarWallet.StarWallet.service.impl.UserServiceImpl;
import com.StarWallet.StarWallet.utility.MockUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
class UserServiceUnitTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userService;

	@Test
	void findByIdTest() {
		Optional<User> mockUser = MockUtility.getMockOptionalUser();
		Mockito.when(userRepository.findById(1L)).then(
				invocationOnMock -> mockUser
		);
		User verifiedUser = userService.findById(1L);
		System.out.println("User is :" + verifiedUser);
		Mockito.verify(userRepository).findById(1L);
		Assert.notNull(verifiedUser, "User should not be null.");
		Assert.isInstanceOf(User.class, verifiedUser);
		Assertions.assertEquals(mockUser.get().getFirstName(), verifiedUser.getFirstName());
		Assertions.assertEquals(mockUser.get().getLastName(), verifiedUser.getLastName());
		Assertions.assertEquals(mockUser.get().getUserType(), verifiedUser.getUserType());
		Assertions.assertEquals(mockUser.get().getBirthDate(), verifiedUser.getBirthDate());
		Assertions.assertEquals(mockUser.get().getMobileNumber(), verifiedUser.getMobileNumber());
		Assertions.assertEquals(mockUser.get().getEmailAddress(), verifiedUser.getEmailAddress());
		Assertions.assertEquals(mockUser.get().getAddresses(), verifiedUser.getAddresses());
		Assertions.assertEquals(mockUser.get().getWallets(), verifiedUser.getWallets());
	}

	@Test
	void deleteUserWithIdTest() throws Exception {
		Optional<User> mockUser = MockUtility.getMockOptionalUser();
		Mockito.when(userRepository.findById(1L)).thenReturn(mockUser);
		Mockito.when(userRepository.save(mockUser.get())).thenReturn(mockUser.get());

		Boolean userDeleteStatus = userService.deleteUserWithId(1L);
		System.out.println("Marked user active status as: " + userDeleteStatus);

		Mockito.verify(userRepository).findById(1L);
		Mockito.verify(userRepository).save(mockUser.get());

		Assertions.assertEquals(Boolean.FALSE, userDeleteStatus, "User not deleted by id.");
	}

}
