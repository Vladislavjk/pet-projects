# DL-Pet-Projects

## CNN classification
  * Implemented CNN using PyTorch and Keras frameworks on Dogs vs Cats dataset.
  * Showed validation curves and tuned hyperparams for Keras CNN.
  * Used Transfer Learning idea to get 96% accuracy (used Xception and then fine tuned with low learning rate).

## Flood Segmentation
  * Trained U-Net and FPN models for segmentation problem on Flood Segmentation dataset.
  * Used pretrained encoder (efficientnetb2 NN) on ImageNet dataset.
  * Showed Loss and IoU score curves and predicted mask on test example for both models.

## NLP IMDB Reviews
  * Data preprocessing (lemmatizing, removing urls, html tags, stopwords and using bigrams).
  * Trained Bidirectional LSTM, showed validation curves and tested on self-made reviews.
  * Trained Bidirectional LSTM with Word2Vec embeddings (embeddings trained on IMDB dataset), showed validation curves and tested on self-made reviews.
  * Used t-SNE to visualize Word2Vec embeddings.
  * Trained Bidirectional LSTM with pre-trained FastText embeddings from gensim, showed validation curves and tested on self-made reviews.
  * Trained BERT model using PyTorch framework.
