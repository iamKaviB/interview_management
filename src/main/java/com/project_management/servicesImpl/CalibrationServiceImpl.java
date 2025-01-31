package com.project_management.servicesImpl;

import com.project_management.dto.CalibrationAnswerDto;
import com.project_management.dto.CalibrationDto;
import com.project_management.dto.CalibrationTestResultDto;
import com.project_management.models.CalibrationQuestions;
import com.project_management.models.CalibrationTest;
import com.project_management.models.UserBasics;
import com.project_management.repositories.CalibrationQuestionRepository;
import com.project_management.repositories.CalibrationTestRepository;
import com.project_management.services.CalibrationService;
import com.project_management.services.UserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalibrationServiceImpl implements CalibrationService {
    @Autowired
    CalibrationQuestionRepository calibrationQuestionRepository;
    @Autowired
    CalibrationTestRepository calibrationTestRepository;
    @Autowired
    UserBasicService userBasicService;

    @Override
    public CalibrationDto getCalibrationTest(Long userId) {
        Random random = new Random();
        // Generate a random 5-digit number
        int number = 10000 + random.nextInt(90000);  // This ensures the number is always 5 digits.

        UserBasics basics = userBasicService.getUserBasicsByUserId(userId);
        List<CalibrationQuestions> fullQuestions = calibrationQuestionRepository.findAllByJobRoleAndExperienceAndPrimarySkill(basics.getJobRole(),basics.getExperience(),basics.getPrimarySkill());
        // Shuffle the list to randomize the order
        Collections.shuffle(fullQuestions);
        // Get the first five elements, making sure the list has at least 5 items
        List<CalibrationQuestions> randomFiveQuestions = fullQuestions.size() > 5
                ? fullQuestions.subList(0, 5)
                : fullQuestions;
        List<CalibrationTest> testList = new ArrayList<>();
        randomFiveQuestions.forEach(calibrationQuestions -> {
            CalibrationTest temp = new CalibrationTest();
            temp.setUserId(userId);
            temp.setTestId((long) number);
            temp.setQuestionId(calibrationQuestions.getId());
            testList.add(temp);
        });
        calibrationTestRepository.saveAll(testList);
        CalibrationDto dto = new CalibrationDto();
        dto.setQuestionsList(randomFiveQuestions);
        dto.setTestId((long) number);
        return dto;
    }

    @Override
    public CalibrationTestResultDto getResultOfTest(CalibrationAnswerDto answerDto) {
        List<CalibrationTest> test = calibrationTestRepository.findAllByTestId(answerDto.getTestId());
        List<Long> questionsIdList  = new ArrayList<>();
        test.forEach(calibrationTest -> {
            questionsIdList.add(calibrationTest.getQuestionId());
        });
        List<CalibrationQuestions> questionsList = calibrationQuestionRepository.findAllById(questionsIdList);
        // Create a map of question ID to CalibrationQuestions for easier lookup
        Map<Long, CalibrationQuestions> questionMap = questionsList.stream()
                .collect(Collectors.toMap(CalibrationQuestions::getId, question -> question));

        int correctCount = 0;
        int wrongCount = 0;

        // Loop through the answer list and check against the correct answer
        for (CalibrationAnswerDto.CalAnswerDto answer : answerDto.getAnswerList()) {
            CalibrationQuestions question = questionMap.get(answer.getQuestionId());

            if (question != null) {
                if (isCorrectAnswer(question, answer.getAnswer())) {
                    correctCount++;
                } else {
                    wrongCount++;
                }
            }
        }

        // Calculate points (this could be more complex based on your scoring logic)
        double points = calculatePoints(correctCount, questionsList.size());

        // Create and return the result DTO
        CalibrationTestResultDto resultDto = new CalibrationTestResultDto();
        resultDto.setTestId(answerDto.getTestId());
        resultDto.setCorrectAnswers(correctCount);
        resultDto.setWrongAnswers(wrongCount);
        resultDto.setPoints(points);

        return resultDto;
    }

    private boolean isCorrectAnswer(CalibrationQuestions question, Long selectedAnswerId) {
        // Check if the selected answer ID matches the correct answer (stored as an integer in correctAnswer)
        return question.getCorrectAnswer().equals(String.valueOf(selectedAnswerId));
    }

    // Calculate points based on the number of correct answers
    private double calculatePoints(int correctCount, int totalQuestions) {
        // Simple scoring example: 1 point per correct answer
        return correctCount * 1.0; // You can adjust this logic as needed
    }
}
