package com.project_management.utils;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class TextToSpeechUtil {

    public void convertTextToMp3(String text, String outputFileName) throws IOException {
        // Initialize the Text-to-Speech client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the input text to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // Set the voice parameters: Language code and Gender (Neutral in this case)
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            // Set audio config to specify output format (MP3 in this case)
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // Perform the text-to-speech request
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // Get the audio content (MP3 data) from the response
            ByteString audioContents = response.getAudioContent();

            // Write the audio content to the specified MP3 file
            try (OutputStream out = new FileOutputStream(outputFileName)) {
                out.write(audioContents.toByteArray());
            }
        }
    }
}
